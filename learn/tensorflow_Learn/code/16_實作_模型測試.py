import tensorflow as tf
from tensorflow import keras
from tensorflow.keras import datasets
import os

# 設定 terminal 只輸出 error 訊息 (指定為 '1' 表示輸出全部訊息)
os.environ['TF_CPP_MIN_LOG_LEVEL'] = '2'

# train dataset 資料預處理
def map_func_train(x, y):
    x = tf.cast(x, dtype=tf.float32)/255
    x = tf.reshape(x, [-1]) # [28, 28] => [28*28]
    y = tf.cast(y, dtype=tf.int32)
    y = tf.one_hot(y, depth=10)
    return x, y

# test dataset 資料預處理
def map_func_test(x, y):
    x = tf.cast(x, dtype=tf.float32)/255
    x = tf.reshape(x, [-1]) # [28, 28] => [28*28]
    y = tf.cast(y, dtype=tf.int32)
    return x, y

def get_mnist_dataset():
    # 載入 mnist 手寫數字資料集，共70k
    # [60k, 28, 28], [10k, 28, 28]
    (x, y), (x_test, y_test) = datasets.mnist.load_data()
    # train dataset
    ds_train = tf.data.Dataset.from_tensor_slices((x, y))
    ds_train = ds_train.map(map_func_train).batch(64)
    # test dataset
    ds_test = tf.data.Dataset.from_tensor_slices((x_test, y_test))
    ds_test = ds_test.map(map_func_test).batch(64) 
    return ds_train, ds_test


ds_train, ds_test = get_mnist_dataset()

# 建立 3層 全連接神經網路，設定表準差為 0.1 以防梯度爆炸
# layer 1: input:784, output: 256
w1 = tf.Variable(tf.random.truncated_normal([784, 256], stddev=0.1))
b1 = tf.Variable(tf.zeros([256]))
# layer 2: input:256, output: 128
w2 = tf.Variable(tf.random.truncated_normal([256, 128], stddev=0.1))
b2 = tf.Variable(tf.zeros([128]))
# layer 3: input:128, output: 10
w3 = tf.Variable(tf.random.truncated_normal([128, 10], stddev=0.1))
b3 = tf.Variable(tf.zeros([10]))

# 設定 learn range 值:修正步伐
lr = 1e-3 # 0.001 = (1*10^-3)

for epoch in range(3): # 設定 ds_train 資料集重複訓練次數
    for step, (x, y) in enumerate(ds_train): # ds_train 的每一個 batch
        with tf.GradientTape() as tape: # 建立求導監聽

            # 將 x 輸入網路層中
            # input layer 1 : [128, 784] => [128, 256]
            l1 = x@w1+b1
            l1 = tf.nn.relu(l1) # Relu 非線型函數: max(0, l1)

            # input layer 2 : [128, 256] => [128, 128]
            l2 = l1@w2+b2
            l2 = tf.nn.relu(l2) # Relu 非線型函數: max(0, l1)
            
            # input layer 3 : [128, 128] => [128, 10]
            out = l2@w3+b3
            
            # 求預測誤差 mean(sum((y-out)^2))
            loss = tf.square(y-out)
            loss = tf.reduce_mean(loss)

        # 向前傳播修正 loss
        grads = tape.gradient(loss, [w1, b1, w2, b2, w3, b3])

        # 將梯度範式限幅在 15，解決 Exploding/Vanishing 的狀況
        grads, _ = tf.clip_by_global_norm(grads, 15)

        # w' = w - lr * w_grads
        # 使用 assign_sub() 原地修正誤差可保持原來的 Variable 類型，否則會變回 Tensor 類型需要再次封裝
        w1.assign_sub(lr * grads[0])
        b1.assign_sub(lr * grads[1]) 
        w2.assign_sub(lr * grads[2])
        b2.assign_sub(lr * grads[3])
        w3.assign_sub(lr * grads[4])
        b3.assign_sub(lr * grads[5])

        # 打印當前訓練結果
        if step % 100 == 0:
            print(epoch, step, 'loss: ', float(loss))

    total_correct, total_num = 0, 0
    for step, (x_test, y_test) in enumerate(ds_test):
        
        # [b, 784] => [b, 256] => [b, 128] => [b, 10]
        h1 = tf.nn.relu(x_test@w1+b1)
        h2 = tf.nn.relu(h1@w2+b2)
        out = h2@w3+b3 # [b, 10]

        # 讓每一個 b of [10] 機率之合為 1
        prob = tf.nn.softmax(out, axis=1)
        # 取得機率最大的索引
        pred = tf.argmax(prob, axis=1) # [b, 10] => [b] (dtype=int64)
        pred = tf.cast(pred, dtype=tf.int32) # int64 -> int32

        # 測試預測與真實結果 (結果為 boolean 型態)
        correct = tf.equal(pred, y_test)
        # 轉換為整數型態
        correct = tf.cast(correct, dtype=tf.int32)
        # 計算預測正確數量
        correct = tf.reduce_sum(correct)

        total_correct += int(correct)
        total_num += x.shape[0]

    acc = total_correct / total_num
    print('test acc: ', acc)
    

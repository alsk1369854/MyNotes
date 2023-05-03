# import tensorflow as tf

# # tf.maximum()|minimum()|clip_by_value() 限制上下限 ========================================
# data = tf.range(-5, 6)
# print(data)
# # tf.Tensor([-5 -4 -3 -2 -1  0  1  2  3  4  5], shape=(11,), dtype=int32)

# # tf.maximum() 限制下限
# result = tf.maximum(data, 2)
# print(result)
# # tf.Tensor([2 2 2 2 2 2 2 2 3 4 5], shape=(11,), dtype=int32)

# # tf.minimum() 限制上限
# result = tf.minimum(data, -2)
# print(result)
# # tf.Tensor([-5 -4 -3 -2 -2 -2 -2 -2 -2 -2 -2], shape=(11,), dtype=int32)

# # tf.clip_by_value() 限制上下限
# result = tf.clip_by_value(t=data, clip_value_min=-3, clip_value_max=3)
# print(result)
# # tf.Tensor([-3 -3 -3 -2 -1  0  1  2  3  3  3], shape=(11,), dtype=int32)

# # tf.clip_by_norm() 範數限幅 =======================================================================
# data = tf.random.normal([2,2], mean=10)
# print('before: ==================')
# print(data)
# # tf.Tensor(
# # [[ 8.764772 11.38187 ]
# #  [ 9.955431 10.063976]], shape=(2, 2), dtype=float32)
# print('norm: ', tf.norm(data))
# # norm:  tf.Tensor(20.168352, shape=(), dtype=float32)

# print('after: ===================')
# result = tf.clip_by_norm(t=data, clip_norm=15)
# print(result)
# # tf.Tensor(
# # [[6.5187073 8.465147 ]
# #  [7.4042473 7.4849763]], shape=(2, 2), dtype=float32)
# print('norm: ', tf.norm(result))
# # norm:  tf.Tensor(15.0, shape=(), dtype=float32)


# 13-3. tf.clip_by_global_norm() 全局的範數限幅 ==============================================================
import tensorflow as tf
from tensorflow import keras
from tensorflow.keras import datasets
import os

# 設定 terminal 只輸出 error 訊息 (指定為 '1' 表示輸出全部訊息)
os.environ['TF_CPP_MIN_LOG_LEVEL'] = '2'

# 載入 mnist 手寫數字資料集，共60k
# x = [60k, 28, 28]
# y = [60k]
(x, y), _ = datasets.mnist.load_data()

# 將數據轉換為 Tensor 物件
x = tf.convert_to_tensor(x, dtype=tf.float32) 
y = tf.convert_to_tensor(y, dtype=tf.int32) 

# 查看 x, y 數據集中的最大最小值
print(x.shape, y.shape, x.dtype, y.dtype) # (60000, 28, 28), (60000,), <dtype: 'float32'>, <dtype: 'int32'>
print(tf.reduce_min(x), tf.reduce_max(x)) # tf.Tensor(0.0, shape=(), dtype=float32), tf.Tensor(255.0, shape=(), dtype=float32)
print(tf.reduce_min(y), tf.reduce_max(y)) # tf.Tensor(0, shape=(), dtype=int32), tf.Tensor(9, shape=(), dtype=int32)

# 故意加大輸入範圍，使其出現 Exploding/Vanishing 的狀況
# 將 x 數據轉換到 0~5 之間 [0~255.] => [0~5.]
x = x / 50
# 將 y 數據傳換成 one_hot [60k] => [60k, 10]
y = tf.one_hot(y, depth=10)
# 設定 learn range 值:修正步伐
lr = 1e-2 # 0.01 = (1*10^-2)

# 建立訓練資料集
train_db = tf.data.Dataset.from_tensor_slices((x,y))
# 設定一次 128 筆資料同時訓練，默認為 batch(1)
train_db = train_db.batch(128)

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

print(w1.device) # Device:GPU

for epoch in range(3): # 設定 train_db 資料集重複訓練次數
    for step, (x, y) in enumerate(train_db): # train_db 的每一個 batch
        # [128, 28, 28] => [128, 784]
        x = tf.reshape(x, [-1, 28*28])

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




import tensorflow as tf

# MSE
# Cross Entropy Loss
# Hinge Loss

# # MSE ---------------
# y = tf.constant([1, 2, 3, 0, 2])
# y = tf.one_hot(y, depth=4)
# y = tf.cast(y, dtype=tf.float32)

# out = tf.random.normal([5, 4])

# loss1 = tf.reduce_mean(tf.square(y-out))
# print(loss1) # tf.Tensor(0.44715032, shape=(), dtype=float32)
# loss2 = tf.square(tf.norm(y-out))/(5*4)
# print(loss2) # tf.Tensor(0.4471503, shape=(), dtype=float32) 
# loss3 = tf.reduce_mean(tf.losses.MSE(y, out))
# print(loss3) # tf.Tensor(0.44715032, shape=(), dtype=float32)


# # Cross Entropy Loss -------------------------
# # Entropy 熵 =========================================================
# # 驚喜度低，熵高
# out1 = tf.constant([0.25, 0.25, 0.25, 0.25])
# entropy1 = -tf.reduce_sum(out1*(tf.math.log(out1)/tf.math.log(2.)))
# print(entropy1) # tf.Tensor(2.0, shape=(), dtype=float32)
# # 驚喜度中，熵中等
# out2 = tf.constant([0.1, 0.1, 0.7, 0.1])
# entropy2 = -tf.reduce_sum(out2*(tf.math.log(out2)/tf.math.log(2.)))
# print(entropy2) # tf.Tensor(1.3567796, shape=(), dtype=float32)
# # 驚喜度高，熵低
# out3 = tf.constant([0.01, 0.97, 0.01, 0.01])
# entropy3 = -tf.reduce_sum(out3*(tf.math.log(out3)/tf.math.log(2.)))
# print(entropy3) # tf.Tensor(0.24194068, shape=(), dtype=float32)

# # Categorical Cross Entropy (分類問題交叉熵)
# # 函數計算使用
# # 一.誤差大
# y1 = tf.constant([0, 1, 0, 0], dtype=tf.float32)
# out1 = tf.constant([0.25, 0.25, 0.25, 0.25])
# loss1 = tf.losses.categorical_crossentropy(y1, out1, from_logits=True)
# print(loss1) # tf.Tensor(1.3862944, shape=(), dtype=float32)

# # 二.誤差小
# y2 = tf.constant([0, 1, 0, 0], dtype=tf.float32)
# out2 = tf.constant([0.01, 0.97, 0.01, 0.01])
# loss2 = tf.losses.categorical_crossentropy(y2, out2, from_logits=True)
# print(loss2) # tf.Tensor(0.764853, shape=(), dtype=float32)

# # 物件類別計算使用
# # 誤差中等
# y3 = tf.constant([0, 1, 0, 0], dtype=tf.float32)
# out3 = tf.constant([0.1, 0.7, 0.1, 0.1])
# ccn = tf.losses.CategoricalCrossentropy()
# loss3 = ccn(y3, out3)
# print(loss3) # tf.Tensor(0.35667497, shape=(), dtype=float32)


# # Binary Cross Entropy (二分類問題交叉熵)
# # 函數計算使用
# # 一.誤差大
# y1 = tf.constant([1], dtype=tf.float32)
# out1 = tf.constant([0.1])
# loss1 = tf.losses.binary_crossentropy(y1, out1, from_logits=True)
# print(loss1) # tf.Tensor(0.64439666, shape=(), dtype=float32)

# # 二.誤差小
# y2 = tf.constant([1], dtype=tf.float32)
# out2 = tf.constant([0.9])
# loss2 = tf.losses.binary_crossentropy(y2, out2, from_logits=True)
# print(loss2) # tf.Tensor(0.3411539, shape=(), dtype=float32)

# # 物件類別計算使用
# # 誤差中等
# y3 = tf.constant([1], dtype=tf.float32)
# out3 = tf.constant([0.7])
# bcn = tf.losses.BinaryCrossentropy()
# loss3 = bcn(y3, out3)
# print(loss3) # tf.Tensor(0.3566748, shape=(), dtype=float32)


# 誤差數值穩定性預防 
# y 必須是 one_hot encoding
# 網路最後一層輸出結果為 logit (未經過 softmax) 
# 設定參數 from_logit=True 表示預測結果為 logit

# 模擬網路層輸出
y = tf.constant([[0., 1.]]) # one_hot encoding
x = tf.random.normal([1, 784])
w = tf.random.normal([784, 2])
b = tf.zeros([2])

logit = x@w+b
print('logit: ', logit) 
# logit:  tf.Tensor([[ 16.132626 -11.485327]], shape=(1, 2), dtype=float32)

# (推薦)方法一: 使用 from_logit=true Tensorflow 內部處理數值穩定性問題
loss_with_logit = tf.losses.categorical_crossentropy(y_true=y, y_pred=logit, from_logits=True)
print('loss_with_logit: ', loss_with_logit)
# loss_with_logit:  tf.Tensor([27.617952], shape=(1,), dtype=float32)

# (不推薦)方法二: 自己使用 softmax 將 logit 轉換為 prob (有風險會出現 NaN 數值不穩定問題)
prob = tf.math.softmax(logit)
print('porb: ', prob)
# porb:  tf.Tensor([[1.0000000e+00 1.0131545e-12]], shape=(1, 2), dtype=float32)
loss_with_prob = tf.losses.categorical_crossentropy(y_true=y, y_pred=prob)
print('loss_with_prob: ', loss_with_prob)
# loss_with_prob:  tf.Tensor([16.118095], shape=(1,), dtype=float32)

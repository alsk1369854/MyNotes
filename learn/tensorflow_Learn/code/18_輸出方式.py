import tensorflow as tf

# # 18-1. tf.sigmoid() 將輸出解果控制在 0~1 之間
# # ==========================================
# data = tf.linspace(-2,2,5)
# # tf.Tensor([-2. -1.  0.  1.  2.], shape=(5,), dtype=float64)

# result = tf.sigmoid(data)
# print(result)
# # tf.Tensor([0.11920292 0.26894142 0.5        0.73105858 0.88079708], shape=(5,), dtype=float64)


# # 18-2. tf.nn.sortmax() 使所有預測結果之合為 1
# # ==========================================
# data = tf.linspace(-2,2,5)
# # tf.Tensor([-2. -1.  0.  1.  2.], shape=(5,), dtype=float64)

# result = tf.nn.softmax(data)
# print(result)
# # tf.Tensor([0.01165623 0.03168492 0.08612854 0.23412166 0.63640865], shape=(5,), dtype=float64)



# 18-3. tf.tanh() 將輸出解果控制在 -1~1 之間
# ==========================================
data = tf.linspace(-2,2,5)
# tf.Tensor([-2. -1.  0.  1.  2.], shape=(5,), dtype=float64)

result = tf.tanh(data)
print(result)
# tf.Tensor([-0.96402758 -0.76159416  0.          0.76159416  0.96402758], shape=(5,), dtype=float64)


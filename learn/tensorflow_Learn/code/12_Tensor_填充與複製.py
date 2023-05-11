import tensorflow as tf

# 12-1. tf.pad() : 維度邊緣填充 ========================================================
# data = tf.reshape(tf.range(9), shape=[3,3])
# print(data)
# # tf.Tensor(
# # [[0 1 2]
# #  [3 4 5]
# #  [6 7 8]], shape=(3, 3), dtype=int32)

# # 對填充維度 "1" 填充1個單位，右填充2個單位 
# result = tf.pad(tensor=data, paddings=[[0,0], [1,2]])
# print(result)
# # tf.Tensor(
# # [[0 0 1 2 0 0]
# #  [0 3 4 5 0 0]
# #  [0 6 7 8 0 0]], shape=(3, 6), dtype=int32)

# # tensor 向外填充1個單位
# result = tf.pad(tensor=data, paddings=[[1,1], [1,1]])
# print(result)
# # tf.Tensor(
# # [[0 0 0 0 0]
# #  [0 0 1 2 0]
# #  [0 3 4 5 0]
# #  [0 6 7 8 0]
# #  [0 0 0 0 0]], shape=(5, 5), dtype=int32)

# # 指定填充值
# result = tf.pad(tensor=data, paddings=[[1,1], [0,0]], constant_values=5)
# print(result)
# # tf.Tensor(
# # [[5 5 5]
# #  [0 1 2]
# #  [3 4 5]
# #  [6 7 8]
# #  [5 5 5]], shape=(5, 3), dtype=int32)


# 12-2. tf.tile() : 真實複製元素 ======================================================
# data = tf.reshape(tf.range(9), shape=[3,3])
# print(data)
# # tf.Tensor(
# # [[0 1 2]
# #  [3 4 5]
# #  [6 7 8]], shape=(3, 3), dtype=int32)

# # 第一維度複製為原 tensor 的兩倍
# result = tf.tile(input=data, multiples=[1,2])
# print(result)
# # tf.Tensor(
# # [[0 1 2 0 1 2]
# #  [3 4 5 3 4 5]
# #  [6 7 8 6 7 8]], shape=(3, 6), dtype=int32)

# # 第二維度複製為原 tensor 的兩倍
# result = tf.tile(input=data, multiples=[2,1])
# print(result)
# # tf.Tensor(
# # [[0 1 2]
# #  [3 4 5]
# #  [6 7 8]
# #  [0 1 2]
# #  [3 4 5]
# #  [6 7 8]], shape=(6, 3), dtype=int32)



# 12-3. tf.broadcast_to() : 參照複製元素 =============================================================
data = tf.reshape(tf.range(9), shape=[3,3])
print(data)
# tf.Tensor(
# [[0 1 2]
#  [3 4 5]
#  [6 7 8]], shape=(3, 3), dtype=int32)
data = tf.expand_dims(data, axis=0)
print(data)
# tf.Tensor(
# [[[0 1 2]
#   [3 4 5]
#   [6 7 8]]], shape=(1, 3, 3), dtype=int32)

result = tf.broadcast_to(input=data, shape=[2,3,3])
print(result)
# tf.Tensor(
# [[[0 1 2]
#   [3 4 5]
#   [6 7 8]],
#  [[0 1 2]
#   [3 4 5]
#   [6 7 8]]], shape=(2, 3, 3), dtype=int32)

import tensorflow as tf

# # sort() ===========================================
# data = tf.random.uniform([3, 3], maxval=10, dtype=tf.int32)
# print(data)
# # tf.Tensor(
# # [[0 8 2]
# #  [4 3 2]
# #  [2 0 5]], shape=(3, 3), dtype=int32)

# # 升序 (預設: direction='ASCENDING')
# result = tf.sort(data)
# print(result)
# # tf.Tensor(
# # [[0 2 8]
# #  [2 3 4]
# #  [0 2 5]], shape=(3, 3), dtype=int32)

# # 降序 (direction='DESCENDING')
# result = tf.sort(data, direction='DESCENDING')
# print(result)
# # tf.Tensor(
# # [[8 2 0]
# #  [4 3 2]
# #  [5 2 0]], shape=(3, 3), dtype=int32)

# # 指定維度排序 (axis: 操作維度)
# result = tf.sort(data, axis=0)
# print(result)
# # tf.Tensor(
# # [[0 0 2]
# #  [2 3 2]
# #  [4 8 5]], shape=(3, 3), dtype=int32)


# # argsort(): 取得排序解果對映原數據的索引 Tensor ===========================================================
# data = tf.random.uniform([3, 3], maxval=10, dtype=tf.int32)
# print(data)
# # tf.Tensor(
# # [[5 2 5]  
# #  [9 7 7]
# #  [0 7 4]], shape=(3, 3), dtype=int32)

# # 升序 (預設: direction='ASCENDING')
# result = tf.argsort(data)
# print(result)
# # tf.Tensor(
# # [[1 0 2]
# #  [1 2 0]
# #  [0 2 1]], shape=(3, 3), dtype=int32)

# # 降序 (direction='DESCENDING')
# result = tf.argsort(data, direction='DESCENDING')
# print(result)
# # tf.Tensor(
# # [[0 2 1]
# #  [0 1 2]
# #  [1 2 0]], shape=(3, 3), dtype=int32)

# # 指定維度排序 (axis: 操作維度)
# result = tf.argsort(data, axis=0)
# print(result)
# # tf.Tensor(
# # [[2 0 2]
# #  [0 1 0]
# #  [1 2 1]], shape=(3, 3), dtype=int32)


# tf.math.top_k(): 取得排序後的前 k 個值 ======================================================
data = tf.random.uniform([3, 3], maxval=10, dtype=tf.int32)
print(data)
# tf.Tensor(
# [[8 8 1]
#  [1 6 7]
#  [4 2 3]], shape=(3, 3), dtype=int32)

# 取排序後前 2 個最大值
result = tf.math.top_k(data, 2)
print(len(result)) # 2
print(result[0]) # 同等於 result.values
# tf.Tensor(
# [[8 8]
#  [7 6]
#  [4 3]], shape=(3, 2), dtype=int32)
print(result[1]) # 同等於 result.indices
# tf.Tensor(
# [[0 1]
#  [2 1]
#  [0 2]], shape=(3, 2), dtype=int32)





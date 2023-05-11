import tensorflow as tf

# # 8-1. 合併方法 ==================================================
# # data = [students, scores]
# data_1 = tf.ones([2, 4])
# # tf.Tensor(
# # [[1. 1. 1. 1.]
# #  [1. 1. 1. 1.]], shape=(2, 4), dtype=float32)
# data_2 = tf.zeros([1, 4])
# # tf.Tensor([[0. 0. 0. 0.]], shape=(1, 4), dtype=float32)

# # concat() 兩 Tensor 除了合併維度外其餘維度大小必須相同 [2, 4]&[1, 4] => [3,4]
# # axis: 合併維度
# result = tf.concat([data_1, data_2], axis=0)
# print(result)
# # tf.Tensor(
# # [[1. 1. 1. 1.]
# #  [1. 1. 1. 1.]
# #  [0. 0. 0. 0.]], shape=(3, 4), dtype=float32)

# # stack() 兩 Tensor 所有維度必須相同，在 axis=0 擴展合併 = [2, 4]&[2, 4] => [2, 2, 4]
# # axis: 合併後擴展的維度
# data_2 = tf.zeros([2, 4])
# # tf.Tensor(
# # [[0. 0. 0. 0.]
# #  [0. 0. 0. 0.]], shape=(2, 4), dtype=float32)

# result = tf.stack([data_1, data_2], axis=0)
# print(result)
# # tf.Tensor(
# # [[[1. 1. 1. 1.]
# #   [1. 1. 1. 1.]]
# #  [[0. 0. 0. 0.]
# #   [0. 0. 0. 0.]]], shape=(2, 2, 4), dtype=float32)


# # 8-2. 分割方法 =======================================================================
# # data = [classes, students, scores]
# data = tf.random.normal([2, 3, 4])

# # split() : 平均拆分 axis=2 的維度 4/2 = 2
# # axis: 指定拆分維度
# result = tf.split(data, axis=2, num_or_size_splits=2)
# print(len(result)) # 2
# print(result[0].shape) # (2, 3, 2)
# print(result[1].shape) # (2, 3, 2)

# # split() : 指定拆分大小
# result = tf.split(data, axis=2, num_or_size_splits=[1, 1, 2])
# print(len(result)) # 3
# print(result[0].shape) # (2, 3, 1)
# print(result[1].shape) # (2, 3, 1)
# print(result[2].shape) # (2, 3, 2)


# # unstack() : 展開擴展維度
# # axis: 拆解的擴展維度
# result = tf.unstack(data, axis=0)
# print(len(result)) # 2
# print(result[0].shape) # (3, 4)
# print(result[1].shape) # (3, 4)








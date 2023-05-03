import tensorflow as tf

# # 5-1. broadcast_to() 使用範例 ==========================================================================
# # data = [classes, students, subjects]
# data = tf.ones([2,2,3])
# # tf.Tensor(
# # [[[1. 1. 1.]
# #   [1. 1. 1.]]
# #  [[1. 1. 1.]
# #   [1. 1. 1.]]], shape=(2, 2, 3), dtype=float32)

# # 使用 + 運算符可自動的完成 Tensor 的 broadcast
# # 可在目標 Tensor 維度中插入 1 元素來自動計算廣展維度，成為目的 Tensor 維度

# # (一)使用 + 運算符自動完成broadcast與矩陣相加
# # 將每個課目分數各加 5 分
# _add = tf.fill([3], 5.)
# print(_add)
# # tf.Tensor([5. 5. 5.], shape=(3,), dtype=float32)
# result = data + _add
# print(result)
# # tf.Tensor(
# # [[[6. 6. 6.]
# #   [6. 6. 6.]]
# #  [[6. 6. 6.]
# #   [6. 6. 6.]]], shape=(2, 2, 3), dtype=float32)


# # (二)使用 + 運算符自動完成broadcast與矩陣相加
# # 將每個課目分數各加 5 分
# _add = tf.fill([1,2,1], 5.)
# print(_add)
# # tf.Tensor(
# # [[[5.]
# #   [5.]]], shape=(1, 2, 1), dtype=float32)
# result = data + _add
# print(result)
# # tf.Tensor(
# # [[[6. 6. 6.]
# #   [6. 6. 6.]]
# #  [[6. 6. 6.]
# #   [6. 6. 6.]]], shape=(2, 2, 3), dtype=float32)

# # 使用 broadcast_to() 將 [3] -> [2,2,3]
# data = tf.ones([3])
# print(data)
# # tf.Tensor([1. 1. 1.], shape=(3,), dtype=float32)
# result = tf.broadcast_to(data, [2,2,3])
# print(result)
# # tf.Tensor(
# # [[[1. 1. 1.]
# #   [1. 1. 1.]]
# #  [[1. 1. 1.]
# #   [1. 1. 1.]]], shape=(2, 2, 3), dtype=float32)

# 5-2. tile() 使用範例 ==============================================================
# data = [classes, students, subjects]
data = tf.ones([1,2,3])
print(data)
# tf.Tensor(
# [[[1. 1. 1.]
#   [1. 1. 1.]]], shape=(1, 2, 3), dtype=float32)

# 可在 tile() 方法中傳入 "multiples" 維度，內可插入 1 元素來自動計算廣展維度

# 複製成兩個班
result = tf.tile(data, multiples=[2,1,1])
print(result)
# tf.Tensor(
# [[[1. 1. 1.]
#   [1. 1. 1.]]
#  [[1. 1. 1.]
#   [1. 1. 1.]]], shape=(2, 2, 3), dtype=float32)
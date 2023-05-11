import tensorflow as tf
import numpy as np

# # 基本索引方式 ======================================================
# [case, b, h, w, rgb]
# a = tf.random.normal([5, 10, 28, 28, 3])
# print(a[0][1].shape)
# print(a[0, 1].shape)


# # 3-2. Start:End =====================================================
# # 1. 單維度操作
# a = tf.range(10)
# # tf.Tensor([0 1 2 3 4 5 6 7 8 9], shape=(10,), dtype=int32)
# print(a[:-1])
# # tf.Tensor([0 1 2 3 4 5 6 7 8], shape=(9,), dtype=int32)
# print(a[1:])
# # tf.Tensor([1 2 3 4 5 6 7 8 9], shape=(9,), dtype=int32)
# print(a[1:9])
# # tf.Tensor([1 2 3 4 5 6 7 8], shape=(8,), dtype=int32)

# # 2. 多維度操作, 取所有圖片 R 通道 
# # [case, b, h, w, rgb]
# b = tf.random.normal([5, 10, 28, 28, 3])
# print(b[:, :, :, :, 0].shape)
# # (5, 10, 28, 28)



# # 3-3. Start:End:Step ==================================================
# # 1. 單維度操作
# a = tf.range(10)
# # tf.Tensor([0 1 2 3 4 5 6 7 8 9], shape=(10,), dtype=int32)
# print(a[::-1])
# # tf.Tensor([9 8 7 6 5 4 3 2 1 0], shape=(10,), dtype=int32)
# print(a[1:-1:2])
# # tf.Tensor([1 3 5 7], shape=(4,), dtype=int32)

# # 2. 多維度操作, 取一半圖片資料並且只取 R 通道
# # [case, b, h, w, rgb]
# b = tf.random.normal([5, 10, 28, 28, 3])
# print(b[:, ::2, :, :, 0].shape)
# # (5, 5, 28, 28)


# # 3-4. "..." 省略號====================================================
# # [case, b, h, w, rgb]
# a = tf.random.normal([5, 10, 28, 28, 3])

# # 取所有圖片 R 通道 
# print(a[...,0].shape)
# # (5, 10, 28, 28)

# # 取第一個 case, 第一張圖的 R 通道
# print(a[0,0,...,0].shape)
# # (28, 28)

# # 3-5. gather() =======================================================
# # data = [classes, students, subjects]
# data = tf.random.uniform([4,35,8], maxval=100, dtype=tf.int32)

# # axis: 操作索引, indices: 取值索引
# # 取班級 0, 3
# print(tf.gather(data, axis=0, indices=[0, 3]).shape)
# # (2, 35, 8)

# # 取每個班級的 3, 7, 11 號學生
# print(tf.gather(data, axis=1, indices=[3, 7, 11]).shape)
# # (4, 3, 8)

# # 3-6. gather_nd() =====================================================
# # data = [classes, students, subjects]
# data = tf.random.uniform([4,35,8], maxval=100, dtype=tf.int32)

# # indices: 取值索引
# # 取以下條件
# #   0 班級 1,2 號學生
# #   1 班級 4,5 號學生
# print(tf.gather_nd(data, indices=[[0,1], [0,2], [1,4], [1,5]]).shape)
# # (4, 8)

# 3-7. boolean_mask() ====================================================
# data = [classes, students, subjects]
data = tf.random.uniform([4,3,2], maxval=100, dtype=tf.int32)

# axis: 操作索引, mask: 是否留下此索引
# 取班級 2,3
result = tf.boolean_mask(data, axis=0, mask=[False, False, True, True])
print(result.shape)
# (2, 3, 2)

# 取以下條件
#   每個班級 0 號學生，第 1 個科目
#   每個班級 1 號學生，第 0 個科目
#   2 號不取
result = tf.boolean_mask(data, axis=1, mask=[
        [False, True], 
        [True, False],
        [False, False]])
print(result.shape)
# (4, 2)



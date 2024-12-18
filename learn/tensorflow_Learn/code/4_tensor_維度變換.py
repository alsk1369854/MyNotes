import tensorflow as tf

# # 4-1. reshape() 維度升降 ==============================================================
# # data = [b, h, w, rgb]
# data = tf.random.normal([5, 28, 28, 3])

# # 一般降維
# result = tf.reshape(data, [5, 28*28, 3])
# print(result.shape)
# # (5, 784, 3)

# # 使用 -1, 自動計算降維, "-1"只能出現一次
# result = tf.reshape(data, [5, -1, 3])
# print(result.shape)
# # (5, 784, 3)

# # 升維
# #   [5, 2352] -> [5, 28, 28, 3]
# b = tf.reshape(data, [5, -1]) # (5, 2352)
# result = tf.reshape(b, [5, 28, 28, 3])
# print(result.shape)
# # (5, 28, 28, 3)


# # 4-2. transpose() 維度調換 ======================================================================================
# # data = [b, h, w, rgb]
# data = tf.random.normal([5, 28, 28, 3])

# # perm: 維度調換index|預設為反轉
# # 維度反轉
# #   [b, h, w, rgn] -> [rgb, w, h, b]
# result = tf.transpose(data)
# print(result.shape)
# # (3, 28, 28, 5)

# # 維度反轉: 高寬交換
# #   [b, h, w, rgn] -> [b, w, h, rgn]
# result = tf.transpose(data, perm=[0, 2, 1, 3])
# print(result.shape)
# # (5, 28, 28, 3)

# # 維度反轉: 通道在前
# #   [b, h, w, rgn] -> [b, rgb, h, w]
# result = tf.transpose(data, perm=[0, 3, 1, 2])
# print(result.shape)
# # (5, 3, 28, 28)


# # 4-3. expand_dims() 維度提升 ==============================
# # data = [classes, students, subjects]
# data = tf.random.uniform([4,35,8], maxval=100, dtype=tf.int32)

# # axis: 操作插入維度(會在 "axis" 與 "axis-1" 之間插入 1 個維度)
# # axis正號: 向前插入
# result = tf.expand_dims(data, axis=0)
# print(result.shape)
# # (1, 4, 35, 8)

# # axis負號: 向後插入
# result = tf.expand_dims(data, axis=-3)
# print(result.shape)
# # (4, 1, 35, 8)


# 4-4. squeeze() 維度下降 =====================================
data = tf.zeros([1, 2, 1, 1, 3])

# axis: 操作維度|預設全部維度, (可將元素為 1 的維度消除)
# 不指定 axis 消除 Tensor 中所有元素為 1 的維度
result = tf.squeeze(data)
print(result.shape)
# (2, 3)

# 消除指定元素為 1 的維度
result = tf.squeeze(data, axis=-2)
print(result.shape)
# (1, 2, 1, 3)









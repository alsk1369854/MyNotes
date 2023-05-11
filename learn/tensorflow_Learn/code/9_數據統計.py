import tensorflow as tf

# # 9-1. 向量范數 ==========================================================================================
# data = tf.convert_to_tensor([[2, 1, 3],[2, 1, 3]], dtype=tf.float32)
# print(data)
# # tf.Tensor(
# # [[2. 1. 3.]
# #  [2. 1. 3.]], shape=(2, 3), dtype=float32)

# # ||x||1  (ord=1) ============================
# result = tf.norm(data, ord=1)
# print(result)
# # tf.Tensor(12.0, shape=(), dtype=float32)

# # ||x||1 指定維度求解
# # 維度索引 1 看 Row
# result = tf.norm(data, ord=1, axis=1)
# print(result)
# # tf.Tensor([6. 6.], shape=(2,), dtype=float32)

# # 維度索引 0 看 Column
# result = tf.norm(data, ord=1, axis=0)
# print(result)
# # tf.Tensor([4. 2. 6.], shape=(3,), dtype=float32)


# # ||x||2  (default: ord=2) ===================
# # 方法一:
# result = tf.norm(data)
# print(result)
# # tf.Tensor(5.2915025, shape=(), dtype=float32)

# # 方法二:
# result = tf.sqrt(tf.reduce_sum(tf.square(data)))
# print(result)
# # tf.Tensor(5.2915025, shape=(), dtype=float32)

# # ||x||2 指定維度求解
# # 維度索引 1 看 Row
# result = tf.norm(data, ord=2, axis=1)
# print(result)
# # tf.Tensor([3.7416573 3.7416573], shape=(2,), dtype=float32)

# # 維度索引 0 看 Column
# result = tf.norm(data, ord=2, axis=0)
# print(result)
# # tf.Tensor([2.828427  1.4142135 4.2426405], shape=(3,), dtype=float32)


# # 9-2. 取(最大/最小/平均)值 ===================================================================================
# data = tf.convert_to_tensor([[2, 1, 3],[2, 1, 3]], dtype=tf.float32)
# print(data)
# # tf.Tensor(
# # [[2. 1. 3.]
# #  [2. 1. 3.]], shape=(2, 3), dtype=float32)

# # reduce_max()
# # 默認全局
# result = tf.reduce_max(data)
# print(result)
# # tf.Tensor(3.0, shape=(), dtype=float32)

# # 特定維度
# result = tf.reduce_max(data, axis=1)
# print(result)
# # tf.Tensor([3. 3.], shape=(2,), dtype=float32)


# # reduce_mix()
# # 默認全局
# result = tf.reduce_min(data)
# print(result)
# # tf.Tensor(1.0, shape=(), dtype=float32)

# # 特定維度
# result = tf.reduce_min(data, axis=1)
# print(result)
# # tf.Tensor([1. 1.], shape=(2,), dtype=float32)


# # reduce_mean()
# # 默認全局
# result = tf.reduce_mean(data)
# print(result)
# # tf.Tensor(2.0, shape=(), dtype=float32)

# # 特定維度
# result = tf.reduce_mean(data, axis=0)
# print(result)
# # tf.Tensor([2. 1. 3.], shape=(3,), dtype=float32)


# # 9-3. 最大與最小值索引 ==============================================================================
# data = tf.convert_to_tensor([[2, 1, 3],[3, 1, 2]], dtype=tf.float32)
# print(data)
# # tf.Tensor(
# # [[2. 1. 3.]
# #  [2. 1. 3.]], shape=(2, 3), dtype=float32)

# # argmax()
# # 預設 axis=0
# result = tf.argmax(data)
# print(result)
# # tf.Tensor([1 0 0], shape=(3,), dtype=int64)

# # 指定維度
# result = tf.argmax(data, axis=1)
# print(result)
# # tf.Tensor([2 0], shape=(2,), dtype=int64)

# # argmin()
# # 預設 axis=0
# result = tf.argmin(data)
# print(result)
# # tf.Tensor([0 0 1], shape=(3,), dtype=int64)

# # 指定維度
# result = tf.argmax(data, axis=1)
# print(result)
# # tf.Tensor([2 0], shape=(2,), dtype=int64)


# # 9-4. 數據比較 ==================================================================
# data_1 = tf.constant([2, 1, 3, 0, 4])
# # tf.Tensor([2 1 3 0 4], shape=(5,), dtype=int32)
# data_2 = tf.range(5)
# # tf.Tensor([0 1 2 3 4], shape=(5,), dtype=int32)
# print(data_1)
# print(data_2)

# result = tf.equal(data_1, data_2)
# print(result)
# # tf.Tensor([False  True False False  True], shape=(5,), dtype=bool)


# # 應用 計算準確率 ====================================
# out = tf.constant(
#     [[0.7, 0.1, 0.2],
#     [0.05, 0.05, 0.9]])
# y = tf.constant([1, 2])

# # 取每個資料機率最大的索引位置
# max_index = tf.cast(tf.argmax(out, axis=1), dtype=tf.int32)
# # tf.Tensor([0 2], shape=(2,), dtype=int32)

# # 比較正確結果
# compare = tf.equal(y, max_index)
# # tf.Tensor([False  True], shape=(2,), dtype=bool)

# # 計算正確數量
# right_count = tf.reduce_sum(tf.cast(compare, dtype=tf.int32))
# # tf.Tensor(1, shape=(), dtype=int32)

# # 預測正確率為 0.5 
# correct_rate = right_count / out.ndim
# # tf.Tensor(0.5, shape=(), dtype=float64)


# 9-5. 去重複 =========================================================================================================
data = tf.constant([4, 2, 2, 4, 3])

# unique()
result = tf.unique(data)
print(len(result)) # 2
print(result[0]) # tf.Tensor([4 2 3], shape=(3,), dtype=int32)
print(result[1]) # tf.Tensor([0 1 1 0 2], shape=(5,), dtype=int32)


# 還原
reduction = tf.gather(result[0], result[1])
print(reduction)
# tf.Tensor([4 2 2 4 3], shape=(5,), dtype=int32)
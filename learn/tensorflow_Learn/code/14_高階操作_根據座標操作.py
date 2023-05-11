# import tensorflow as tf

# # 14-1. tf.where(): 取座標位址 ===================================================================================
# # 真值取值(根據座標取值) ===================================
# data = tf.random.normal([2,2])
# print(data)
# # tf.Tensor(
# # [[-1.3280066  -0.26919734]
# #  [ 0.45089418 -0.5666146 ]], shape=(2, 2), dtype=float32)
# mask = tf.constant([[True, False],
#                     [False, True]])

# # 取 mask 座標位置
# position = tf.where(condition=mask)
# print(position)
# # tf.Tensor(
# # [[0 0]
# #  [1 1]], shape=(2, 2), dtype=int64)

# # 根據 座標位置取值
# result = tf.gather_nd(data, position)
# print(result)
# # tf.Tensor([-1.3280066 -0.5666146], shape=(2,), dtype=float32)


# # 真值與非真值分別取值 ======================================
# data_one = tf.ones([2,2])
# data_zero = tf.zeros([2,2])

# mask = tf.constant([[True, False],
#                     [False, True]])

# result = tf.where(condition=mask, x=data_one, y=data_zero)
# print(result)
# # tf.Tensor(
# # [[1. 0.]
# #  [0. 1.]], shape=(2, 2), dtype=float32)


# # 14-2. tf.scatter_nd(): 根據座標更新值 ========================================================================
# # 二維 tensor 更新
# indices = tf.constant([[1],[3]])
# updates = tf.constant([3, 6])
# shape = tf.constant([5])

# result = tf.scatter_nd(indices=indices, updates=updates, shape=shape)
# print(result)

# # 三維 tensor 更新
# indices = tf.constant([[0],[2]])
# updates = tf.ones([2,2,2])
# shape = tf.constant([4,2,2])

# result = tf.scatter_nd(indices=indices, updates=updates, shape=shape)
# print(result)
# # tf.Tensor(
# # [[[1. 1.]
# #   [1. 1.]],
# #  [[0. 0.]
# #   [0. 0.]],
# #  [[1. 1.]
# #   [1. 1.]],
# #  [[0. 0.]
# #   [0. 0.]]], shape=(4, 2, 2), dtype=float32)


# # # 14-3. tf.meshgrid(): 生成座標點 ===================================================================
# # 生成 x:0~2, y0~2 的所有整數座標點
# x = tf.linspace(start=0, stop=2, num=3) # 0~2, 取3個點
# y = tf.linspace(start=0, stop=2, num=3) # 0~2, 取3個點

# points_x, points_y = tf.meshgrid(x, y)
# print(points_x)
# # tf.Tensor(
# # [[0. 1. 2.]
# #  [0. 1. 2.]
# #  [0. 1. 2.]], shape=(3, 3), dtype=float64)
# print(points_y)
# # tf.Tensor(
# # [[0. 0. 0.]
# #  [1. 1. 1.]
# #  [2. 2. 2.]], shape=(3, 3), dtype=float64)

# stack = tf.stack([points_x, points_y], axis=-1)
# print(stack.shape) # (3, 3, 2)

# result = tf.reshape(tensor=stack, shape=[-1, 2])
# print(result)
# # tf.Tensor(
# # [[0. 0.]
# #  [1. 0.]
# #  [2. 0.]
# #  [0. 1.]
# #  [1. 1.]
# #  [2. 1.]
# #  [0. 2.]
# #  [1. 2.]
# #  [2. 2.]], shape=(9, 2), dtype=float64)



# 14-4. 運用 tf.meshgrid() 繪製 z = sin(x) + sin(y) =============================================================
import tensorflow as tf
import matplotlib.pyplot as plt

def func(x):
    # z = sin(x) + sin(y)
    z = tf.math.sin(x[...,0]) + tf.math.sin(x[...,1])
    return z

# [500]
x = tf.linspace(0., 2*3.14, 500)
y = tf.linspace(0., 2*3.14, 500)

# [500, 500]
point_x, point_y = tf.meshgrid(x, y)

# [500, 500, 2]
points = tf.stack([point_x, point_y], axis=-1)
print('points shape: ', points.shape)

# [500, 500]
z = func(points)
print('z shape: ', z.shape)

plt.figure('plot 2d func value')
plt.imshow(z, origin='lower', interpolation='none')
plt.colorbar()

plt.figure('plot 2d func contour')
plt.contour(point_x, point_y, z)
plt.colorbar()
plt.show()




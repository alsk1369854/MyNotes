import tensorflow as tf

w = tf.constant(1.)
x = tf.constant(2.)
b = tf.constant(0.0)
y = x*w

# 基礎使用 (Basic)
# # 錯誤
# with tf.GradientTape() as tape:
#     tape.watch([w])
#     [w_grad] = tape.gradient(y, [w]) # 未被包含在 tf.GradientTape() 範圍內，因此無法求導
#     print(w_grad) # [None]

# # 正確使用 gradient
# with tf.GradientTape() as tape:
#     tape.watch([w]) # 監聽要梯度的 tensor 物件
#     y2 = x*w # 要求導的函數宣告在 tf.GradientTape() 範圍內
#     [w_grad] = tape.gradient(target=y2, sources=[w]) # y2 對 w 進行求導
#     print(w_grad) # tf.Tensor(2.0, shape=(), dtype=float32)


# 執著的 Persistent
# 設定 persistent 為 True 將會使 gradient() 可被重複呼叫
# with tf.GradientTape(persistent=True) as tape:
#     tape.watch([w, x])
#     y2 = x*w
#     [w_grad] = tape.gradient(target=y2, sources=[w])
#     print(w_grad)  # tf.Tensor(2.0, shape=(), dtype=float32)

#     # 可重複調用 gradient() 方法進行求導
#     [x_grad] = tape.gradient(target=y2, sources=[x])
#     print(x_grad) # tf.Tensor(1.0, shape=(), dtype=float32)


# 二階求導
with tf.GradientTape() as t1:
    t1.watch([w])

    with tf.GradientTape() as t2:
        t2.watch([w, b])
        y = x*w+b
        # 一階求導
    [dy_dw, dy_db] = t2.gradient(y, [w, b])
    print(dy_dw) 

        # 可重複調用 gradient() 方法進行求導
d2y_dw2 = t1.gradient(dy_dw, [w])
print(d2y_dw2)  # tf.Tensor(1.0, shape=(), dtype=float32)

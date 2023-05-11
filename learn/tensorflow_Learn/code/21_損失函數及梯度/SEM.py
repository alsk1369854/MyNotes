import tensorflow as tf

x = tf.random.normal([2, 4])
print("x:")
print(x, "\n")
w = tf.random.normal([4, 3])
b = tf.zeros([3])

y = tf.constant([2, 0])

with tf.GradientTape() as tape:
    tape.watch([w, b])
    one_hot = tf.one_hot(y, depth=3)
    print("one_hot:")
    print(one_hot, "\n")
    prob = tf.nn.softmax(x@w+b, axis=1)
    print("prob:")
    print(prob, "\n")
    loss_MSE = tf.losses.MSE(one_hot, prob)
    loss_mean = tf.reduce_mean(loss_MSE)

grads = tape.gradient(loss_mean, [w, b])
print("grads[0]:")
print(grads[0] ,"\n")
print("grads[1]:")
print(grads[1] , "\n")

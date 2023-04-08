## 梯度下降 (Gradient)

### 基本使用 (Basic)

- 求導函數
  
  - 必須宣告在 with tf.GradientTape() 區域內

- 要求導的變數
  
  - 必須在 with tf.GradientTape() 進行監聽

- 使用 gradList = gradinet(target, sources) 函數進行求導
  
  - target = 求導函數
  
  - sources = [求導變數1, 求導變數2, ...]
  
  - gradList = [對變數1的求導結果, 對變數2的求導結果, ...]

```python
import tensorflow as tf

w = tf.constant(1.)
x = tf.constant(2.)
y = x*w

# 錯誤
with tf.GradientTape() as tape:
    tape.watch([w])
    [w_grad] = tape.gradient(y, [w]) # 未被包含在 tf.GradientTape() 範圍內，因此無法求導
    print(w_grad) # [None]

# 正確使用 gradient
with tf.GradientTape() as tape:
    tape.watch([w]) # 監聽要梯度的 tensor 物件
    y2 = x*w # 要求導的函數宣告在 tf.GradientTape() 範圍內
    [w_grad] = tape.gradient(target=y2, sources=[w]) # y2 對 w 進行求導
    print(w_grad) # tf.Tensor(2.0, shape=(), dtype=float32)
```



### 

### 重複求導

- 開啟重複求導功能 tf.GradientTape(persistent=True)
  
  - 創建 gradientTage 區域時，傳入 persistent=True 參數

- 在區域內可重複調用 gradient 進行求導

```python
with tf.GradientTape(persistent=True) as tape:
    tape.watch([w, x])
    y2 = x*w
    [w_grad] = tape.gradient(target=y2, sources=[w])
    print(w_grad)  # tf.Tensor(2.0, shape=(), dtype=float32)

    # 可重複調用 gradient() 方法進行求導
    [x_grad] = tape.gradient(target=y2, sources=[x]) 
    print(x_grad) # tf.Tensor(1.0, shape=(), dtype=float32)

```



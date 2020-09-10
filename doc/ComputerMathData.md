# MarkDown 数学公式语法大全
- 单行左对齐公式
$\sum_{i=0}^N\int_{a}^{b}g(t,i)\text{d}t$

- 跨行居中公式
$$
\sum_{i=0}^N\int_{a}^{b}g(t,i)\text{d}t
$$

- 无边框矩阵
$$
\begin{matrix}
1 & 0 & 0 \\  
0 & 1 & 0 \\  
0 & 0 & 1 \\  
\end{matrix}
$$

- 小括号边框矩阵
$$
\begin{pmatrix}
1 & 0 & 0 \\  
0 & 1 & 0 \\  
0 & 0 & 1 \\  
\end{pmatrix}
$$

- 大括号边框矩阵
$$
\begin{Bmatrix}
1 & 0 & 0 \\  
0 & 1 & 0 \\  
0 & 0 & 1 \\  
\end{Bmatrix}
$$

- 双竖线边框矩阵
$$
\begin{Vmatrix}
1 & 0 & 0 \\  
0 & 1 & 0 \\  
0 & 0 & 1 \\  
\end{Vmatrix}
$$

- 中括号边框矩阵
$$
\begin{bmatrix}
1 & 0 & 0 \\  
0 & 1 & 0 \\  
0 & 0 & 1 \\  
\end{bmatrix}
$$

- 行列式边框矩阵
$$
\begin{vmatrix}
1 & 0 & 0 \\  
0 & 1 & 0 \\  
0 & 0 & 1 \\  
\end{vmatrix}
$$

- m*n省略号矩阵
$$
\begin{bmatrix}
{a_{11}}&{a_{12}}&{\cdots}&{a_{1n}} \\
{a_{21}}&{a_{22}}&{\cdots}&{a_{2n}} \\
{\vdots}&{\vdots}&{\ddots}&{\vdots} \\
{a_{m1}}&{a_{m2}}&{\cdots}&{a_{mn}} \\
\end{bmatrix}
$$

- 常用集合符号函数矩阵
$$
\begin{bmatrix}
{\sin x}&{\ln x}&{\max(A,B)}&{\infty}&{\cup} \\
{\cap}&{\subset}&{\subseteq}&{\supset}&{\in} \\
{\notin}&{\varnothing}&{\forall}&{\exists}&{\lnot} \\
{\nabla}&{\partial}&{a \quad b}&{a \ b}&{a_{xyz}} \\
\end{bmatrix}
$$



- 常用微积分符号矩阵
$$
\begin{pmatrix}
{\sum_{i=1}^n{a_i}}&{\prod \frac{1}{i^2}}&{\lim_{x\to 0}}&{\prime} \\
{\int_0^\infty{f(x)dx}}&{\mathrm{d}}&{\int ^2_3 x^2 {rm d}x}&{\iint} \\
{\iiiint}&{\oint}{\frac{x}{y}}&&{\sqrt[x]{y}} \\
\end{pmatrix}
$$






- 泰勒级数
$$
e^{x} = 1 + \frac{x}{1!} + \frac{x^{2}}{2!} + \frac{x^{3}}{3!} + \cdots , \quad - \infty < x < \infty
$$

- 余弦公式
$$
\cos 2\theta = \cos^2 \theta - \sin^2 \theta = 2 \cos^2 \theta
$$ 

- 集合公式 
$$
M(\beta^{\ast}(D),D) \subseteq C
$$

- 求和公式  
$$
\sum_{i=0}^n i^2 = \frac{(n^2+n)(2n+1)}{6}
$$

- 平方根公式  
$$
x = \dfrac{-b \pm \sqrt{b^2 - 4ac}}{2a}
$$  

- 条件函数
$$
f(n) =
\begin{cases}
\frac{n}{2},  & \text{if $n$ is even} \\
3n+1, & \text{if $n$ is odd}
\end{cases}
$$

- 偏导数公式
$$
\begin{equation}
\begin{split}
\frac{\partial^2 f}{\partial{x^2}} &= \frac{\partial(\Delta_x f(i,j))}{\partial x} = \frac{\partial(f(i+1,j)-f(i,j))}{\partial x} \\
&= \frac{\partial f(i+1,j)}{\partial x} - \frac{\partial f(i,j)}{\partial x} \\
&= f(i+2,j) -2f(f+1,j) + f(i,j)
\end{split}
\nonumber
\end{equation}
$$

- 统计学公式带编号
$$
\begin{equation}
\sum_{i=0}^n F_i \cdot \phi (H, p_i) - \sum_{i=1}^n a_i \cdot ( \tilde{x_i}, \tilde{y_i}) + b_i \cdot ( \tilde{x_i}^2 , \tilde{y_i}^2 ) \tag{1.2.3}
\end{equation}
$$

$$
\begin{equation}
\beta^*(D) = \mathop{argmin} \limits_{\beta} \lambda {||\beta||}^2 + \sum_{i=1}^n max(0, 1 - y_i f_{\beta}(x_i)) \tag{1.2.4}
\end{equation}
$$


$$
\begin{equation}
\sum_{i=0}^n F_i \cdot \phi (H, p_i) - \sum_{i=1}^n a_i \cdot ( \tilde{x_i}, \tilde{y_i}) + b_i \cdot ( \tilde{x_i}^2 , \tilde{y_i}^2 ) \tag{1.2.5}
\end{equation}
$$


$$
\beta^*(D) = \mathop{argmin} \limits_{\beta} \lambda {||\beta||}^2 + \sum_{i=1}^n max(0, 1 - y_i f_{\beta}(x_i)) \tag{1.2.6}
$$

$$z = (p_0, ..... , p_n) \tag{公式21} $$  
$$ s = r cos(a+b) = r cos(a) cos(b) - r sin(a) sin(b) \tag{1.1} $$  
$$ t = r sin(a+b) = r sin(a) cos(b) - r cos(a) sin(b) \tag{1.2} $$

- 极限
$$ \max \limits_{a<x<b}\{f(x)\} $$

- 好玩矩阵
$$
\begin{matrix}
{\lceil \frac{x}{2} \rceil} & {\lfloor \frac{x}{2} \rfloor} & x^2 \\
{\lbrace \rbrace} & {\langle,\rangle} & y^2 \\
{\mathop{a}\limits_{i=1}} & z & z^2 \\
\end{matrix}
$$

- 行内小矩阵
$$\bigl( \begin{smallmatrix} a & b \\ c & d \end{smallmatrix} \bigr)$$

- 数组带分割线
$$
\left[
    \begin{array}{cc|c}
      1&2&3\\
      4&5&6
    \end{array}
\right] 
$$

- 矩阵方程表达式
$$
\left(
\begin{array}{c}
      s \\
      t
\end{array}
\right)
=\left[
\begin{array}{cc}
      cos(b) & -sin(b) \\
      sin(b) & cos(b)
\end{array}
\right]
*\left(
    \begin{array}{c}
      x \\
      y
    \end{array}
\right)
$$

- 向量表达式
$$
\begin{bmatrix}
      {\vec {x}} \\
      {\overrightarrow {x}} \\
      {\overrightarrow {xyzcfcfcf}} \\
      {\overleftarrow {xyzcfcfcfcfcf}}
      {x^2} \\
      {x_i} \\
      {x_{n^2}^{2_n}}
\end{bmatrix}
$$

- 常用希腊字母表达式
$$
\begin{bmatrix}
		{\alpha}&{\beta}&{\gamma}&{\delta}&{\epsilon}&{\zeta}&{\eta}&{\theta} \\
		{\lambda}&{\mu}&{\nu}&{\xi}&{\pi}&{\rho}&{\sigma}&{\kappa} \\
		{\upsilon}&{\phi}&{\chi}&{\psi}&{\omega}&{\tau}&{\iota}&{0} \\
		{\Sigma}&{\Theta}&{\Gamma}&{\Delta}&{\Lambda}&{\Xi}&{\Pi}&{\Upsilon} \\
		{\Phi}&{\Psi}&{\Omega}&{0}&{0}&{0}&{0}&{0}
\end{bmatrix}
$$



## 参考
[MarkDown公式语法](https://www.jianshu.com/p/a0aa94ef8ab2)  
[MarkDown公式语法2](https://cloud.tencent.com/developer/article/1402840)




















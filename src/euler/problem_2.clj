(defn fib-step [[a b]]
  [b (+' a b)])

(defn fib-seq []
  (map first (iterate fib-step [0 1])))

(->>
  (fib-seq)
  (take-while #(< % 4000000))
  (filter even?)
  (apply +')
  println)
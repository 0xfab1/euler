(defn- det-cancel [x y]
  (let [m (quot x 10), n (mod x 10)
        p (quot y 10), q (mod y 10)]
    (cond (= m p) [[x y] m n q]
          (= m q) [[x y] m n p]
          (= n p) [[x y] n m q]
          (= n q) [[x y] n m p]
          :else nil)))

(->> (for [numr (range 10 100)
           denr (range 10 100)
           :when (< numr denr)] [numr denr])
     (map (partial apply det-cancel))
     (remove nil?)
     (remove (fn [[_ z _ q]] (or (zero? z)     ; remove trivial cases
                                 (zero? q))))  ; avoid div/0
     (filter (fn [[[x y] _ p q]] (= (/ x y) (/ p q))))
     (map first)
     (map (partial apply /))
     (apply *))

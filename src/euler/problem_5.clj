(defn lcm [& args]
  (let [gcd (fn [a b]
              (loop [a a, b b]
                (cond
                  (< a b) (recur a (- b a))
                  (> a b) (recur (- a b) b)
                  :else a)))
        lcm- (fn [a b] (/ (* a b) (gcd a b)))
        [x y & more] args]
    (reduce lcm- (lcm- x y) more)))

(println (apply lcm (range 2 21)))
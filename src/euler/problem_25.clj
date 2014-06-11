(defn count-digits [x]
  (->> (iterate inc 1)
       (reductions
         (fn [[out num] d]
           (if (nil? out)
             (let [q (quot num 10)]
               (if (zero? q) [d d] [nil q]))
             [out num])) [nil x])
       (map first)
       (drop-while nil?)
       first))

(defn fib-step [[a b]]
  [b (+' a b)])

(defn fib-seq []
  (rest (map first (iterate fib-step [0 1]))))

(->>
  (fib-seq)
  (interleave (iterate inc 1))
  (partition 2)
  (drop-while #(< (count-digits (second %)) 1000))
  ffirst
  println)
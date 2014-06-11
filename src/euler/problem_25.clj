(defn fib-step [[a b]]
  [b (+' a b)])

(defn fib-seq []
  (rest (map first (iterate fib-step [0 1]))))

(let [thousand-digits (reduce (fn [acc _] (*' 10 acc)) 1 (range 1000))]
  (->>
    (fib-seq)
    (interleave (iterate inc 1))
    (partition 2)
    (drop-while #(< (second %) thousand-digits))
    ffirst
    println))

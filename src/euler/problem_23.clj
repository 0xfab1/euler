(defn sumfactors [x]
  (let [x' (int (Math/sqrt x))
        candidates (range 1 (inc x'))
        result (- (reduce #(if (zero? (mod x %2)) (+ %1 %2 (quot x %2)) %1) 0 candidates) x)]
    (if (= x (* x' x')) (- result x') result)))

(let [n 28123
      nums (range 1 (inc n))
      abundant-nums (->>
                      nums
                      (map sumfactors)
                      (interleave nums)
                      (partition 2)
                      (filter (fn [[p q]] (< p q)))
                      (map first)
                      set)
      not-sum-of-two-abun? (fn [x]
                             (every? nil?
                                     (->>
                                       abundant-nums
                                       (filter #(< % x))
                                       (map #(abundant-nums (- x %)))
                                       )))]
  (->> nums
       (filter not-sum-of-two-abun?)
       (apply +)
       println))
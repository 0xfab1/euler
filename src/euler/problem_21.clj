(defn sumfactors [x]
  (let [x' (int (Math/sqrt x))
        candidates (range 1 (inc x'))
        result (- (reduce #(if (zero? (mod x %2)) (+ %1 %2 (quot x %2)) %1) 0 candidates) x)]
    (if (= x (* x' x')) (- result x') result)))

(let [n 10000
      nums (range 1 n)
      facs (map sumfactors nums)
      numfacs (zipmap nums facs)
      ami? (fn [x]
             (let [nx (numfacs x)]
               (and
                 (not= x nx)                                ;; don't allow self-amicable numbers
                 (= x (numfacs nx)))))]
  (->> nums
       (filter ami?)
       (apply +)
       println))

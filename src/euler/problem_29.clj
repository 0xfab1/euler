(def N 100)
(def nums (atom #{}))

(dorun (for [a (range 2 (inc N))]
         (reduce (fn [acc _]
                   (let [x' (*' acc a)]
                     (do (swap! nums conj x') x'))) a (range 1 N))))

(println (count @nums))

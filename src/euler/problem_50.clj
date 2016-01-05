(defn infinite-primes
  "Generate infinite sequence of primes"
  []
  (let [prime? (fn [x] (and (or (= x 2) (odd? x))
                            (every? #(pos? (mod x %)) (range 3 (inc (Math/sqrt x)) 2))))]
    (->> (iterate (partial + 2) 3)
         (filter prime?)
         (cons 2))))

(defn primes-under
  "Generate sequence of primes less than the given value"
  [x]
  (take-while #(< % x) (infinite-primes)))

(def cache (atom {}))

(defn find-consecutive-components
  "Find the consecutive primes in prime-list that sums to prime.
   Since we start from the smallest element, the first primes found must be the longest."
  [prime-list prime]
  (loop [len 1
         primes (into [] prime-list)]
    (if (contains? @cache prime)
      (@cache prime)
      (let [plen (count primes)                             ; can be optimized
            psum (apply + (take len primes))]               ; can be optimized
        (cond
          (= prime psum) (let [v {:n len :x prime}]
                           (swap! cache assoc prime v)      ; ugly caching
                           v)
          (and (< psum prime) (< len plen)) (recur (inc len) primes)
          (and (> psum prime) (pos? plen)) (recur (dec len) (drop 1 primes))
          :else {:n 0 :x 0})))))

(time
  (let [maxval 1000
        primes (primes-under maxval)
        fun (partial find-consecutive-components primes)
        pairs (->> primes
                   (map fun)
                   (filter #(> (:n %) 1)))                  ; ignore the case where prime=[prime]
        max-n (apply max (map :n pairs))]                   ; longest length
    (->> pairs
         (filter #(= (:n %) max-n))                         ; prime with the longest consecutive components
         println)))

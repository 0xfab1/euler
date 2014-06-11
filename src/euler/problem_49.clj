(defn primes []
  (let [prime? (fn [x] (and (or (= x 2) (odd? x)) (every? #(< 0 (mod x %)) (range 3 (inc (Math/sqrt x)) 2))))]
    (->> (iterate (partial + 2) 3)
         (filter prime?)
         (cons 2))))

(defn triplets []
  (let [primset (->> (primes)
                     (drop-while #(< % 1000))
                     (take-while #(< % 10000))
                     set)]
    (->>
      (for [p primset
            q (disj primset p)
            :when (< p q)]
        (let [r (+ q (- q p))]
          (if (primset r) [p q r])))
      (keep identity))))

(dorun
  (->> (triplets)
       (map #(map (comp (partial apply str) sort str) %))
       (map #(apply = %))
       (interleave (triplets))
       (partition 2)
       (filter second)
       println))

(defn primes []
  (let [prime? (fn [x] (and (or (= x 2) (odd? x)) (every? #(< 0 (mod x %)) (range 3 (inc (Math/sqrt x)) 2))))]
    (->> (iterate (partial + 2) 3)
         (filter prime?)
         (cons 2))))

(->>
  (primes)
  (drop 10000)
  first
  println)
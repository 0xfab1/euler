(defn primes []
  (let [prime? (fn [x] (and (or (= x 2) (odd? x)) (every? #(< 0 (mod x %)) (range 3 (inc (Math/sqrt x)) 2))))]
    (->> (iterate (partial + 2) 3)
         (filter prime?)
         (cons 2))))

(let [n 600851475143]
  (->>
    (primes)
    (take-while #(<= % (Math/sqrt n)))
    (filter #(zero? (mod n %)))
    last
    println))
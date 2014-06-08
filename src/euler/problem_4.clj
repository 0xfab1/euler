(defn palin? [x]
  (let [strx (-> x str list*)]
    (= strx (reverse strx))))

(->>
  (for [p (range 100 1000)
        q (range 100 1000)] (* p q))
  (filter palin?)
  (apply max)
  println)
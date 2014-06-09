(let [n 20
      m-size (inc n)
      m (atom (vec (repeat (* m-size m-size) 0)))
      set-m (fn [i j v] (swap! m assoc (+ (* i m-size) j) v))
      get-m (fn [i j] (@m (+ (* i m-size) j)))]
  (doseq [i (range m-size)]
    (set-m i 0 1)
    (set-m 0 i 1))
  (doseq [i (range 1 m-size)
          j (range 1 m-size)]
    (set-m i j (+ (get-m (dec i) j) (get-m i (dec j)))))
  (println (last @m)))
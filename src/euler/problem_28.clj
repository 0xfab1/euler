(let [total (atom 0)
      x (atom 1)]
  (dorun
    (for [i (range 1 501)
          _ (range 4)]
      (do
        (swap! x + (* 2 i))
        (swap! total + @x))))
  (println (inc @total)))

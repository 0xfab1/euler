(let [n 100
      sq #(*' % %)
      nums (range 1 (inc n))
      sum-o-sq (apply +' (map sq nums))
      sq-o-sum (sq (apply +' nums))]
  (println (- sq-o-sum sum-o-sq)))
(defn sum-pow-digits [x n]
  (->> (str x)
       (map #(Integer/parseInt (str %)))
       (map #(apply * (repeat n %)))
       (apply +)))

(->> (iterate inc 2)
  (map #(list % (sum-pow-digits % 5)))
  (filter #(= (first %) (second %)))
  (take 6)
  (map first)
  (apply +)
  println)


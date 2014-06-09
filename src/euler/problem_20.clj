(defn fact [n]
  (reduce *' 1 (range 1 (inc n))))

(->> (str (fact 100))
     (map #(Integer/parseInt (str %)))
     (apply +)
     println)
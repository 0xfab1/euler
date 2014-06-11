(def cache (atom {}))

(defn sum-sq-digits [x]
  (loop [x' x]
    (cond
      (or (= x' 1) (= x' 89)) (do (swap! cache assoc x x') x')
      (@cache x') (@cache x')
      :else (let [y (->> (str x')
                         (map #(Integer/parseInt (str %)))
                         (map #(* % %))
                         (apply +))]
              (recur y)))))

(->> (range 1 10000000)
     (map sum-sq-digits)
     (filter #(= % 89))
     count
     println)

(defn input []
  (re-seq #"[A-Z]+" (slurp "../../data/problem_22.in")))

(defn rank [name]
  (let [letters (zipmap "ABCDEFGHIJKLMNOPQRSTUVWXYZ" (range 1 27))]
    (apply + (map letters name))))

(let [names (input)]
  (->>
    names
    sort
    (map rank)
    (interleave (range 1 (inc (count names))))
    (partition 2)
    (map #(apply * %))
    (apply +)
    println))

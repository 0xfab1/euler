(require '[clojure.java.io :as io])

(defn input []
  (with-open [rdr (io/reader "../../data/problem_18.in")]
    (doall
      (->>
        (line-seq rdr)
        (map (partial re-seq #"\d+"))
        (map (fn [coll]
               (->>
                 coll
                 (map #(Integer/parseInt %))
                 vec)))
        vec))))

(defn dynaprog [[par & [cur & rest]]]
  (let [par' (vec (concat [0] par [0]))
        result (map-indexed (fn [i v] (+ v (max (par' i) (par' (inc i))))) cur)]
    (if (empty? rest)
      [result]
      (cons result (dynaprog (cons result rest))))))

(->> (input)
     (cons [])
     dynaprog
     last
     (apply max)
     println
     )
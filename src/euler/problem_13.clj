(require '[clojure.java.io :as io])

(defn input []
  (with-open [rdr (io/reader "../../data/problem_13.in")]
    (doall (line-seq rdr))))

(->> (input)
     (map bigint)
     (apply +)
     str
     (take 10)
     (apply str)
     println)
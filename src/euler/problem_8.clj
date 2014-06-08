(require '[clojure.java.io :as io])

(defn input []
  (with-open [rdr (io/reader "../../data/problem_8.in")]
    (->>
      (line-seq rdr)
      (apply str))))

(let [n 13
      nums (->> (input)
                (re-seq #"\d")
                (map #(Integer/parseInt (str %))))
      len (count nums)
      parts (map #(->> nums
                       (drop %)
                       (take n)) (range (inc (- len n))))]
  (->> parts
       (map #(apply * %))
       (apply max)
       println))
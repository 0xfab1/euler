(require '[clojure.java.io :as io]
         '[clojure.string :as str])

(defn- gen-moves [n row col row-fn col-fn]
  (take n (partition 2 (interleave (iterate row-fn row)
                                   (iterate col-fn col)))))

(defn- valid-move [board-size move]
  (every? identity (map (fn [[x y]] (and (< -1 x board-size)
                                         (< -1 y board-size))) move)))

(defn- horz [n row col] (gen-moves n row col identity inc))
(defn- vert [n row col] (gen-moves n row col inc identity))
(defn- diag-r [n row col] (gen-moves n row col inc inc))
(defn- diag-l [n row col] (gen-moves n row col dec inc))

(with-open [rdr (io/reader "data/problem_11.in")]
  (let [N 4
        board (->> (line-seq rdr)
                   (map #(->> (str/split % #" ")
                              (map (fn [x] (Integer/parseInt x)))
                              vec))
                   vec)
        board-size (count board)
        all-points (for [x (range board-size)
                         y (range board-size)] [x y])

        valid-moves (->> [horz vert diag-r diag-l]
                         (mapcat #(map (fn [[x y]] (% N x y)) all-points))
                         (filter #(valid-move board-size %)))

        get-value (fn [[x y]] ((board x) y))
        get-values (fn [move] (map get-value move))
        sum-elems (fn [move] (apply * (get-values move)))]

    (println (->> valid-moves
                  (map (partial get-values))
                  (map (partial apply *))
                  (apply max)))))

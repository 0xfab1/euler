(def cache (atom {}))

(defn collatz
  ([n]
   (cond
     (= n 1) [n]
     (@cache n) (@cache n)
     :else (let [n' (if (even? n) (/ n 2) (inc (* 3 n)))
                 v (cons n (collatz n'))]
             (do
               (swap! cache assoc n v)
               v)))))

(->> (range 1 1000000)
     (map collatz)
     (map-indexed #(list (inc %1) (count %2)))
     (sort-by second >)
     ffirst
     println
     )
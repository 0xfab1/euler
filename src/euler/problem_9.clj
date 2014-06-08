(let [nums (->>
             (for [a (range 1000)
                   b (range 1000) :when (< b a)
                   c (range 1000) :when (< c b)]
               [a b c])
             (filter #(= 1000 (apply + %)))
             (filter (fn [[a b c]] (= (* a a) (+ (* b b) (* c c))))))]
  (println (apply * (first nums))))
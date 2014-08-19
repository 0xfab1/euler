(def words {1  "one"
            2  "two"
            3  "three"
            4  "four"
            5  "five"
            6  "six"
            7  "seven"
            8  "eight"
            9  "nine"
            10 "ten"
            11 "eleven"
            12 "twelve"
            13 "thirteen"
            15 "fifteen"
            18 "eighteen"
            20 "twenty"
            30 "thirty"
            40 "forty"
            50 "fifty"
            80 "eighty"})

(defn number-to-words [x]
  (cond
    (= x 1000) "one thousand"
    (<= x 10) (words x)
    (< x 20) (let [w (words x)]
               (if (nil? w) (str (words (mod x 10)) "teen") w))
    (< x 100) (let [t (quot x 10)
                    x' (mod x 10)
                    w (words (* 10 t))
                    w' (if (nil? w) (str (words t) "ty") w)]
                (str w' (if (zero? x') "" (str " " (number-to-words x'))))
                )
    (< x 1000) (let [h (quot x 100)
                     x' (mod x 100)
                     w (str (number-to-words h) " hundred")]
                 (str w (if (zero? x') "" (str " and " (number-to-words x')))))
    :else ""))

(defn count-letters [text]
  (map count (clojure.string/split text #"\s+")))

(println
  (->>
    (range 1 1001)
    (map number-to-words)
    (map count-letters)
    (flatten)
    (apply +)
    ))
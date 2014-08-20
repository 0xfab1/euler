(defn isleap? [y]
  (if (zero? (mod y 100))
    (zero? (mod y 400)) (zero? (mod y 4))))

(defn ndays [m y]
  ({1  31
    2  (if (isleap? y) 29 28)
    3  31
    4  30
    5  31
    6  30
    7  31
    8  31
    9  30
    10 31
    11 30
    12 31} m))

(defn redfun [[counter dow] [m y]]
  (let [n (ndays m y)
        dow' (mod (+ dow n) 7)]
    (if (zero? dow)
      [(inc counter) dow']
      [counter dow'])))

(println
  (->>
    (for [y (range 1901 2001)
          m (range 1 13)] [m y])
    (reduce redfun [0 2])))
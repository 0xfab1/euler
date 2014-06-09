(let [n 500
      trinums (reductions + (iterate inc 1))
      nfactors (fn [x]
                 (let [sqrt-x (int (Math/sqrt x))
                       candidates (range 1 (inc sqrt-x))]
                   (reduce #(if (zero? (mod x %2)) (+ %1 (if (= (* sqrt-x sqrt-x) x) 1 2)) %1) 0 candidates)))]
  (->>
    trinums
    (map nfactors)
    (interleave trinums)
    (partition 2)
    (drop-while #(< (second %) n))
    ffirst
    println))
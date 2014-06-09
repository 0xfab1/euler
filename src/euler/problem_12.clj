(let [n 500
      trinums (reductions + (iterate inc 1))
      nfactors (fn [x]
                 (let [sqrt-x (int (Math/sqrt x))
                       candidates (range 1 (inc sqrt-x))
                       result (reduce #(if (zero? (mod x %2)) (+ %1 2) %1) 0 candidates)]
                   (if (= x (* sqrt-x sqrt-x)) (dec result) result)))]
  (->>
    trinums
    (map nfactors)
    (interleave trinums)
    (partition 2)
    (drop-while #(< (second %) n))
    ffirst
    println))
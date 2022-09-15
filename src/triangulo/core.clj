(ns triangulo.core
  (:require [clojure.math :as math]))

(defn calc-perimetro
  "Calcula o perimetro do triangulo, dado A B e C"
  [a b c]
  (+ a b c))


(defn calc-angulo
  "TODO: Calcula o ângulo ∠A, dado A B C."
  [a b c]
  (let [quadrado-b (math/pow b 2)
        quadrado-c (math/pow c 2)
        quadrado-a (math/pow a 2)
        divisor (* 2 b c)
        numerador (+ quadrado-b quadrado-c (* -1 quadrado-a))
        arccos (/ numerador divisor)]
    (math/to-degrees (math/acos arccos)))
  )

(defn calc-radianos
  "TODO: Calcular radianos dado lados a b e c de um triangulo"
  [a b c]
  (math/to-radians (calc-angulo a b c))
  )

(defn calc-area
  "TODO: Calcula a área de um triângulo usando a formula de Heron."
  [a b c]
  (let [p (/ (calc-perimetro a b c) 2)
        p-a (- p a)
        p-b (- p b)
        p-c (- p c)
        ]
    (math/sqrt (* p p-a p-b p-c))))

(defn calc-altura
  "TODO: Calcula altura de A, dado a AREA."
  [a area]
  (/ (* area 2) a)
  )

(defn equilateral?
  "TODO: Verifica se o triangulo é equilateral"
  [a b c]
  (= a b c)
  )

(defn isosceles?
  "TODO: Verifica se pelo menos dois lados sao iguais."
  [a b c]
  (cond
    (= a b) true
    (= a c) true
    (= b c) true
    :else false)
  )

(defn escaleno?
  "TODO: Verifica se os lados dos triangulos sao diferentes entre si."
  [a b c]
  (not (= a b c))
  )

(defn retangulo?
  "TODO: Verifica se é um triangulo retangulo, cujos angulos são iguais a 90o.
  O resultado não é exato, dado que cada angulo é arredondado utilizando clojure.math/round."
  [a b c]
  (cond
    (= 90 (math/round (calc-angulo a b c))) true
    (= 90 (math/round (calc-angulo b a c)))true
    (= 90 (math/round (calc-angulo c b a))) true
    :else false
    ))

(defn obtuso?
  "TODO: Verifica se o triangulo é obtuso, tendo algum angulo >90o."
  [a b c]
  (cond
    (< 90 (calc-angulo a b c)) true
    (< 90 (calc-angulo b a c)) true
    (< 90 (calc-angulo c b a)) true
    :else false
    )
  )

(defn agudo?
  "TODO: Verifica se o triangulo é obtuso, tendo algum angulo >90o."
  [a b c]
  (let [
        lado-a (< 90 (calc-angulo a b c))
        lado-b (< 90 (calc-angulo b a c))
        lado-c (< 90 (calc-angulo c b a))]
    (= lado-a lado-b lado-c)
    ))

(defn gerar-dados-completos
  [a b c]
  (let [area (calc-area a b c)]
    {:lados [a b c]
     :retagulo (retangulo? a b c)
     :obtuso (obtuso? a b c)
     :agudo (agudo? a b c)
     :escaleno (escaleno? a b c)
     :isosceles (isosceles? a b c)
     :equilateral (equilateral? a b c)
     :area area
     :altura [(calc-altura a area)
              (calc-altura b area)
              (calc-altura c area)]
     :angulos [(calc-angulo a b c)
               (calc-angulo b c a)
               (calc-angulo c a b)]}))

(comment
 (require 'clojure.pprint)
 (escaleno? 60 51.96152 30)
 (retangulo? 60 51.96152 30)
 (clojure.pprint/pprint (gerar-dados-completos 30 20 44))
 (clojure.pprint/pprint (gerar-dados-completos 60 51.96152 30))
 (clojure.pprint/pprint (gerar-dados-completos 15.14741 28.08887 30))
 )
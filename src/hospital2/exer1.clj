(ns hospital2.exer1
  (:use clojure.pprint))

(defn adiciona-paciente [pacientes paciente]
  (if-let [id (:id paciente)]
    (assoc pacientes id paciente)
    (throw (ex-info "Paciente não possui id" {:áciente paciente}))))


(defn chamando-pacientes []
  (let [pacientes {}
        miguel { :id 15 :nome "Miguel" :nascimento "11/04/06"}
        pedro { :id 20 :nome "Pedro" :nascimento "06/08/04"}
        mateus { :nome "Mateus" :nascimento "23/05/03"}
        ]
    (pprint (adiciona-paciente pacientes miguel))
    (pprint (adiciona-paciente pacientes pedro))
    (pprint (adiciona-paciente pacientes mateus))))


;(chamando-pacientes)


(defrecord Paciente [id nome nascimento])

(println (->Paciente 15 "Miguel" "11/04/06"))
(pprint (->Paciente 15 "Miguel" "11/04/06"))
(pprint (Paciente. 15 "Miguel" "11/04/06"))
(pprint (map->Paciente { :id 15 :nome "Miguel" :nascimento "11/04/06"}))

(let [miguel (->Paciente 15 "Miguel" "11/04/06")]
  (pprint (:id miguel))
   (pprint (vals miguel)))

(pprint (map->Paciente { :id 15 :nome "Miguel" :nascimento "11/04/06" :rg "23456231-5"}))
(pprint (Paciente. nil "Miguel" "11/04/06" ))
(pprint (map->Paciente { :nome "Miguel" :nascimento "11/04/06" :rg "23456231-5"}))
(pprint (assoc (Paciente. nil "Miguel" "11/04/06") :id 7))



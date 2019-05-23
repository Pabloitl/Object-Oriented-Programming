package reto5;

/*
    Periodo: enero-junio (2019)
    Alumno: Pablo Vargas Bermúdez
    Semestre: 2
    Profesor: Carpio Flores Jose Gerardo
*/

interface Race{
    public void run();
}

interface Combat{
    public void attack();
}

interface Water{
    public void swim();
}

interface Road{
    public void ride();
}

interface Air{
    public void fly();
}

interface Hybrid extends Air, Water, Road{
    public void transform();
}

interface RaceHybrid extends Air, Water, Road, Race{
    public void fastTransform();
}

interface CombatHybrid extends Air, Water, Road, Combat{
    public void dangerousTransform();
}

package reto5;

class Tank implements CombatHybrid{
    @Override
    public void attack(){
        System.out.println("Attack as a Tank!!");
    }

    @Override
    public void ride(){
        System.out.println("Riding as a Tank!!");
    }

    @Override
    public void swim(){
        System.out.println("Swimming as a Tank!!");
    }

    @Override
    public void fly(){
        System.out.println("Flying as a Tank!!");
    }
    
    @Override
    public void dangerousTransform(){
        System.out.println("Transforming (Dangerously) as a Tank!!");
    }

    public void showOff(){
        ride();
        swim();
        fly();
        dangerousTransform();
        attack();
    }
}

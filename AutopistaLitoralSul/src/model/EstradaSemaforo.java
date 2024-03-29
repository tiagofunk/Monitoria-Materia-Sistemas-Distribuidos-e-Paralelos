package model;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class EstradaSemaforo extends Estrada{

    private Semaphore semaforo = new Semaphore( 1 );
    
    public EstradaSemaforo(int x, int y, String sentido) {
        super(x, y, sentido);
    }
    
    @Override
    public void reservar(){
        try {
            semaforo.acquire();
//            assert semaforo.availablePermits() == 0:"Número de permissões deveria ser igual a zero.";
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public boolean tentaReservar(){
//        return semaforo.tryAcquire();
        try {
            return semaforo.tryAcquire( new Random().nextInt(TEMPO_TENTATIVA), TimeUnit.MILLISECONDS);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public void avancar(Veiculo v) {
//        assert this.getVeiculo() == null : "Não deveria ter veículo nesse PedacaMapa";
        v.setPedacoMapa(this);
        this.setVeiculo( v );
    }
    
    @Override
    public void liberar(){
        //        assert this.getVeiculo() == null : "Não deveria ter veículo ao dar o release";
//        assert semaforo.availablePermits() == 0:"Número de permissões deveria ser igual a zero.";

        semaforo.release();
    }
    
    @Override
    public void sair(){
        this.setVeiculo(null);
    }
    
}

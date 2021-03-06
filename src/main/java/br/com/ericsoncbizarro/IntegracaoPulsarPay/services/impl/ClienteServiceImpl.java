package br.com.ericsoncbizarro.IntegracaoPulsarPay.services.impl;

import br.com.ericsoncbizarro.IntegracaoPulsarPay.model.modelPulsarPay.Cliente;
import br.com.ericsoncbizarro.IntegracaoPulsarPay.services.ClienteService;
import br.com.ericsoncbizarro.IntegracaoPulsarPay.services.HttpPulsarPayCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private HttpPulsarPayCliente httpPulsarPay;

    private final AtomicLong counter = new AtomicLong();

    public List<Cliente> findAll() throws Exception {
        return httpPulsarPay.getClientes();
    }

    public List<Cliente> create(Cliente clienteRequestBody) throws Exception { // criar um dto para cliente
        Cliente clienteMock = mockCliente(clienteRequestBody);
        return httpPulsarPay.postCliente(clienteMock);
    }

    private Cliente mockCliente(Cliente clienteRequestBody){
        Cliente clienteHttp = new Cliente();
        clienteHttp.setControle_externo(counter.incrementAndGet());
        clienteHttp.setNome(clienteRequestBody.getNome());
        clienteHttp.setDocumento(clienteRequestBody.getDocumento());
        clienteHttp.setData_nascimento(clienteRequestBody.getData_nascimento());
        return clienteHttp;
    }
}
# RabbitMQ

RabbitMQ é um **message broker** implementado em Erlang que utiliza o protocolo AMQP para essa troca de mensagens.

Diferente dos protocolos de mensageira tradicionais, o AMQP utiliza o conceito de *exchanges* (no roteamento das mensagens) e *acknowledges* (para confirmação de recebimento).

![rabbitmq](.github/rabbitmq.png)

Basicamente, os publishers enviam mensagens ao broker para uma exchange, e o broker realiza o roteamento da mensagem para filas existentes com base do tipo de exchange, estas filas serão consumidas pelos consumers que subscreveram nelas. 

# Exchanges

Exchanges funcionam de forma semelhante a um serviço postal, recebem as mensagens e as redirecionam (routing) aos carteiros (queues). 

As regras de roteamento são chamadas de *routing keys*.


> 💡 Uma diferença na analogia do serviço postal é que as mensagens são efetivamente copiadas para as filas, e uma mesma mensagem pode ser roteada para mais de 1 uma fila.


Um producer de mensagens nunca as envia diretamente para filas, ele sempre envia para exchanges. O trabalho da exchange se resume a receber mensagens dos producers e enviar estas mensagens para queues. 

A exchange sabe exatamente o que deve fazer com as mensagens que recebe, se vai enviar para uma ou mais filas, se a mensagem deve ser descartada, etc. Tudo depende do tipo de exchange que recebeu a mensagem.

## Direct

## Fanout

## Topic

## Header

# Queue

No AMQP queues são buffers de mensagens.

A ligação entre uma exchange e uma queue é chamada de *binding*.

## Dead Letter Queues

# Processamento Digital de Imagens

Sistema desenvolvido para abrir, exibir, manipular e salvar imagens RGB com 24 bits/pixel (8 bits/componente/pixel). O sistema tem as seguintes funcionalidades:
1. Conversão RGB-YIQ-RGB.
2. Exibição de bandas individuais (R, G e B) como imagens monocromáticas ou coloridas (em tons de R, G ou B, respectivamente).
3. Negativo. 
4. Controle de brilho multiplicativo (s = r.c, c real não negativo).
5. Convolução m X n com máscara especificada pelo usuário em arquivo texto, como por exemplo, filtros Média e Sobel.
6. Filtro mediana e moda m X n.
7. Rotação e DCT (direta e inversa).
8. Rotação por um ângulo especificado (parâmetro entre 0 e 360 graus), utilizando:

   8.1 mapeamento direto.

   8.2 mapeamento reverso com interpolação bilinear.
   
9. Módulo da DCT, sem o nível DC e o valor do nível DC.
10. Encontra e exibe uma aproximação obtida preservando o coeficiente DC e os n coeficientes AC mais importantes da imagem e zerando os demais. O parâmetro n é um inteiro no intervalo [0, RxC-1].
11. Encontra a imagem resultante da filtragem de uma imagem por um filtro passa-baixa ideal quadrado, com frequência de corte fc (parâmetro especificado pelo usuário) igual à aresta do quadrado, em pixels.

O sistema não faz o uso de bibliotecas prontas para tais fins expecificados acima.

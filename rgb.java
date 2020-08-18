import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

import javax.swing.*;
import java.awt.*;

import javax.imageio.ImageIO;
public class rgb{
	static int red = 0;
	static int green = 1;
	static int blue = 2;
	private static Scanner sc1;
	private static Scanner sc2;
	private static Scanner sc;

    public static void main(String[] args) throws IOException {
		boolean YiqExists = false; // funcionalidade do menu
		BufferedImage picture; // imagem no formato bufferedImage

        String s = iniciarSistema();
        picture = ImageIO.read(new File("Insira seu diretório de imagens aqui" + s));
		int[][][] originalRgb = imageToRgbMatrix(picture);
		int w = picture.getWidth();
		int h = picture.getHeight();
		System.out.println("> Imagem lida com sucesso e convertida para matriz RGB");
		double[][][] matrizYiq = new double[h][w][3]; //Cria uma matriz YIQ vazia
		//Menu
    	do {
    		originalRgb = imageToRgbMatrix(picture);
			System.out.println("\n> Escolha uma opera��o:\n" + "> 1 - Gerar Imagens R, G, B Monocrom�ticas\n" + "> 2 - Gerar Imagens R, G e B coloridas\n"
					+"> 3 - RGB-> YIQ\n" + "> 4 - YIQ-> RGB\n" + "> 5 - Alterar Brilho\n" +"> 6 - Filtro Negativo\n" + "> 7 - Filtro em Txt\n" + "> 8 - Filtro Moda\n" + "> 9 - Filtro Mediana\n"
							+ "> 10 - Filtro m�dia\n" + "> 11 - Recarregar Imagem\n" + "> 12 - Rota��o Direta\n" + "> 13 - Rota��o Inversa\n"
					+ "> 14 - DCT\n" + "> 15 - IDCT\n" + "> 16 - Filtro Quadrado - Fc\n" + "> 17 - N maiores Coeficientes\n" + "> 0 - Sair\n");

			sc2 = new Scanner(System.in);
			int escolha = sc2.nextInt();
			switch(escolha) {

				case 0:{ // Sair
				    System.out.println("Finalizando programa...");
				    System.out.println("Pressione Enter para encerrar o programa!");
				    System.in.read();
	    			System.exit(0);
	    			}
	    			break;
                case 1:{ // Espectros R, G, B Monocrom�ticas
	    			System.out.println("> Criando Imagens R, G, B - Monocrom�ticas");
	    			int[][][] matrizRedCinza = filtrarCorMono(originalRgb,red);
	    			int[][][] matrizGreenCinza = filtrarCorMono(originalRgb, green);
	    			int[][][] matrizBlueCinza = filtrarCorMono(originalRgb, blue);


	    			criarImagemRGB(matrizRedCinza, s + "-RED Mono");
	    			criarImagemRGB(matrizGreenCinza, s +"-GREEN Mono");
	    			criarImagemRGB(matrizBlueCinza, s + "-BLUE Mono");
	    			System.out.println("> Criadas com Sucesso!\n");
                    BufferedImage imagem1 = ImageIO.read(new File("C:\\Users\\Wellyngton\\Desktop\\Wellyngton\\19.2\\Int. ao Processamento Digital de Imagens\\Projeto 01\\Imagens Teste Projeto\\" + s +"-RED Mono.jpg"));
                    BufferedImage imagem2 = ImageIO.read(new File("C:\\Users\\Wellyngton\\Desktop\\Wellyngton\\19.2\\Int. ao Processamento Digital de Imagens\\Projeto 01\\Imagens Teste Projeto\\" + s +"-GREEN Mono.jpg"));
                    BufferedImage imagem3 = ImageIO.read(new File("C:\\Users\\Wellyngton\\Desktop\\Wellyngton\\19.2\\Int. ao Processamento Digital de Imagens\\Projeto 01\\Imagens Teste Projeto\\" + s +"-BLUE Mono.jpg"));
                    exibirImagem(picture, imagem1, imagem2, imagem3);}
	    			break;

	    		case 2:{ // Espectros R, G, B Coloridas
	    			System.out.println("> Criando Imagens R, G, B - Coloridas");
	    			int[][][] matrizRedColorida = filtrarCor(originalRgb,red);
	    			int[][][] matrizGreenColorida = filtrarCor(originalRgb,green);
	    			int[][][] matrizBlueColorida = filtrarCor(originalRgb,blue);

	    			criarImagemRGB(matrizRedColorida, s + "-RED");
	    			criarImagemRGB(matrizGreenColorida, s + "-GREEN");
	    			criarImagemRGB(matrizBlueColorida, s + "-BLUE");
	    			System.out.println("> Criadas com Sucesso!\n");
	    			BufferedImage imagem1 = ImageIO.read(new File("C:\\Users\\Wellyngton\\Desktop\\Wellyngton\\19.2\\Int. ao Processamento Digital de Imagens\\Projeto 01\\Imagens Teste Projeto\\" + s +"-RED.jpg"));
                    BufferedImage imagem2 = ImageIO.read(new File("C:\\Users\\Wellyngton\\Desktop\\Wellyngton\\19.2\\Int. ao Processamento Digital de Imagens\\Projeto 01\\Imagens Teste Projeto\\" + s +"-GREEN.jpg"));
                    BufferedImage imagem3 = ImageIO.read(new File("C:\\Users\\Wellyngton\\Desktop\\Wellyngton\\19.2\\Int. ao Processamento Digital de Imagens\\Projeto 01\\Imagens Teste Projeto\\" + s +"-BLUE.jpg"));
                    exibirImagem(picture, imagem1, imagem2, imagem3);
	    			}
	    			break;

	    		case 3:{ // RGB->YIQ
	    	        matrizYiq = rgbToYiq(originalRgb);
	    	        YiqExists=true;

	    			System.out.println("> Convers�o RGB-YIQ feita com sucesso!\n");
                    }
	    			break;

	    		case 4:{ // YIQ->RGB
	    			if(YiqExists) {
	    				int [][][] matrizRgbTransformada = yiqToRgb(matrizYiq);
		    			criarImagemRGB(matrizRgbTransformada, s+"RGB-Yiq-RGB");
		    			System.out.println("> Convers�o YIQ-RGB feita com sucesso!\n");
		    			BufferedImage imagem1 = ImageIO.read(new File("C:\\Users\\Wellyngton\\Desktop\\Wellyngton\\19.2\\Int. ao Processamento Digital de Imagens\\Projeto 01\\Imagens Teste Projeto\\" + s +"RGB-Yiq-RGB.jpg"));
		    			exibirImagem(picture, imagem1, null, null);
	    			}
	    			else {
		    	     System.out.println("> Primeiro transforme para YIQ!");
	    			}
                    }
	    			break;

	    		case 5:{ // Brilho
	    			System.out.println("> Defina o brilho em porcentagem:");
	    			sc1 = new Scanner(System.in);
	    			int brilho = sc1.nextInt();
	    			System.out.println("> 0 - R\n> 1 - G\n> 2 - B\n> 3 - RGB \n> 4 - Y");
	    			int banda = sc1.nextInt();

	    			if(banda < 4) { //Mudar brilho - RGB
	    				alterarBrilho(originalRgb,brilho, banda);
	    				criarImagemRGB(originalRgb, s +"-Brilho De" + brilho + "%-Banda-"+banda);
	    				System.out.println("> Brilho de " + brilho + "% aplicado!");
	    				BufferedImage imagem1 = ImageIO.read(new File("C:\\Users\\Wellyngton\\Desktop\\Wellyngton\\19.2\\Int. ao Processamento Digital de Imagens\\Projeto 01\\Imagens Teste Projeto\\"+s +"-Brilho De" + brilho + "%-Banda-"+banda+".jpg"));
                        exibirImagem(picture, imagem1, null, null);
	    			}

	    			else if (banda == 4){ //Mudar brilho - YIQ
	    				double[][][] YiqBrilho = rgbToYiq(originalRgb);
	    				brilhoYiq(YiqBrilho,brilho);
	    				int[][][] matrizRgbBrilho = yiqToRgb(YiqBrilho);
	    				criarImagemRGB(matrizRgbBrilho, s + "-Brilho De" + brilho + "%-Banda-"+banda);
	    				System.out.println("> Brilho de " + brilho + "% aplicado!");
	    				BufferedImage imagem1 = ImageIO.read(new File("C:\\Users\\Wellyngton\\Desktop\\Wellyngton\\19.2\\Int. ao Processamento Digital de Imagens\\Projeto 01\\Imagens Teste Projeto\\"+s +"-Brilho De" + brilho + "%-Banda-"+banda+".jpg"));
                        exibirImagem(picture, imagem1, null, null);
					}
					else{
                        System.out.println("> Op��o inv�lida!\n");
					}
                    }
	    			break;

	    		case 6:{ // Negativo
	    			System.out.println("Qual banda? \n> 0 - Red \n> 1 - Green \n> 2 - Blue \n> 3 - RGB \n> 4 - Y");
	    			sc1 = new Scanner(System.in);
	    			int banda = sc1.nextInt();
	    			if(banda < 4) { // se for em RGB
	    				negativo(originalRgb, banda);
	    				criarImagemRGB(originalRgb, s + "-Negativo"+"-Banda-"+banda);
	    				System.out.println("> Negativo aplicado com sucesso!\n");
	    				BufferedImage imagem1 = ImageIO.read(new File("C:\\Users\\Wellyngton\\Desktop\\Wellyngton\\19.2\\Int. ao Processamento Digital de Imagens\\Projeto 01\\Imagens Teste Projeto\\"+s + "-Negativo"+"-Banda-"+banda+".jpg"));
                        exibirImagem(picture, imagem1, null, null);
	    			} else if (banda == 4) {
	    				double[][][] matrizYiqNegativo = rgbToYiq(originalRgb);
	    				negativoYiq(matrizYiqNegativo);
	    				int[][][] matrizRgbNegativo = yiqToRgb(matrizYiqNegativo);
	    				criarImagemRGB(matrizRgbNegativo,s + "-Negativo"+"-Banda-"+banda);
	    				System.out.println("> Negativo aplicado com sucesso!\n");
	    				BufferedImage imagem1 = ImageIO.read(new File("C:\\Users\\Wellyngton\\Desktop\\Wellyngton\\19.2\\Int. ao Processamento Digital de Imagens\\Projeto 01\\Imagens Teste Projeto\\"+s + "-Negativo"+"-Banda-"+banda+".jpg"));
                        exibirImagem(picture, imagem1, null, null);
	    			}
	    			else{
                        System.out.println("> Op��o inv�lida!\n");
	    			}
                    }
	    			break;

	    		case 7:{ //Filtro txt
	    			System.out.println("> Insira o nome do filtro salvo sem extens�o (Ex.: sobel)");
	    			sc = new Scanner(System.in);
	    			String inputString = sc.nextLine();
	    	        File file = new File("C:\\Users\\Wellyngton\\Desktop\\Wellyngton\\19.2\\Int. ao Processamento Digital de Imagens\\Projeto 01\\Imagens Teste Projeto\\" + inputString + ".txt");
                    while(!file.exists()){
                        try{
                            clearConsole();
                        } catch (InterruptedException e){
                            e.printStackTrace();
                        }
                        System.out.println("> Arquivo inexistente!\n");
                        System.out.println("> Insira o nome do filtro salvo sem extens�o (Ex.: sobel)");
                        sc1 = new Scanner(System.in);
                        inputString = sc1.nextLine();
                        file = new File("C:\\Users\\Wellyngton\\Desktop\\Wellyngton\\19.2\\Int. ao Processamento Digital de Imagens\\Projeto 01\\Imagens Teste Projeto\\" + inputString + ".txt");
                    }
	    			float[][] filtro = lerFiltro(inputString); // leu o filtro
	    			int[][][] fotoAjustada = ajustarFoto(originalRgb,filtro[0].length,filtro.length);
	    			int[][][] fotoFiltrada = aplicarFiltro(fotoAjustada, filtro);
	    			criarImagemRGB(fotoFiltrada,s + "Filtro " + inputString);
	    			BufferedImage imagem1 = ImageIO.read(new File("C:\\Users\\Wellyngton\\Desktop\\Wellyngton\\19.2\\Int. ao Processamento Digital de Imagens\\Projeto 01\\Imagens Teste Projeto\\" + s + "Filtro " + inputString + ".jpg"));
                    exibirImagem(picture, imagem1, null, null);}
	    			break;

	    		case 8:{ // Moda
	    			System.out.println("> Defina M na sua matrix MxN:");
	    			sc1 = new Scanner(System.in);
	    			int jSize = sc1.nextInt();
	    			System.out.println("> Defina N na sua matrix MxN:");
	    			int iSize = sc1.nextInt();
	    			int[][][] fotoAjustada = ajustarFoto(originalRgb,iSize,jSize);
	    			int[][][] fotoFiltrada = moda(fotoAjustada,iSize, jSize);
	    			criarImagemRGB(fotoFiltrada,s + "-Filtro Moda");
	    			System.out.println("Filtro aplicado com sucesso!");
	    			BufferedImage imagem1 = ImageIO.read(new File("C:\\Users\\Wellyngton\\Desktop\\Wellyngton\\19.2\\Int. ao Processamento Digital de Imagens\\Projeto 01\\Imagens Teste Projeto\\" + s + "-Filtro Moda" + ".jpg"));
                    exibirImagem(picture, imagem1, null, null);}
	    			break;

	    		case 9:{ // Mediana
	    			System.out.println("> Defina M na sua matrix MxN:");
	    			sc1 = new Scanner(System.in);
	    			int jSize = sc1.nextInt();
	    			System.out.println("> Defina N na sua matrix MxN:");
	    			int iSize = sc1.nextInt();
	    			int[][][] fotoAjustada = ajustarFoto(originalRgb,iSize,jSize);
	    			int[][][] fotoFiltrada = mediana(fotoAjustada,iSize, jSize);
	    			criarImagemRGB(fotoFiltrada,s +"-Filtro Mediana");
	    			System.out.println("Filtro aplicado com sucesso!");
	    			BufferedImage imagem1 = ImageIO.read(new File("C:\\Users\\Wellyngton\\Desktop\\Wellyngton\\19.2\\Int. ao Processamento Digital de Imagens\\Projeto 01\\Imagens Teste Projeto\\" + s +"-Filtro Mediana" + ".jpg"));
                    exibirImagem(picture, imagem1, null, null);}
	    			break;

	    		case 10:{ // M�dia
	    			System.out.println("> Defina M na sua matrix MxN:");
	    			sc1 = new Scanner(System.in);
	    			int jSize = sc1.nextInt();
	    			System.out.println("> Defina N na sua matrix MxN:");
	    			int iSize = sc1.nextInt();
	    			int[][][] fotoAjustada = ajustarFoto(originalRgb,iSize,jSize);
	    			int[][][] fotoMedia = media(fotoAjustada,iSize,jSize);
	    			criarImagemRGB(fotoMedia,s + "-Filtro Media");
	    			System.out.println("> Filtro aplicado com sucesso!\n");
	    			BufferedImage imagem1 = ImageIO.read(new File("C:\\Users\\Wellyngton\\Desktop\\Wellyngton\\19.2\\Int. ao Processamento Digital de Imagens\\Projeto 01\\Imagens Teste Projeto\\" + s + "-Filtro Media" + ".jpg"));
                    exibirImagem(picture, imagem1, null, null);}
	    			break;

                case 11:{ // Mudar de foto
	    			s = iniciarSistema();
	    			picture = ImageIO.read(new File("C:\\Users\\Wellyngton\\Desktop\\Wellyngton\\19.2\\Int. ao Processamento Digital de Imagens\\Projeto 01\\Imagens Teste Projeto\\" + s));
	    			originalRgb = imageToRgbMatrix(picture);
	    			System.out.println("> Imagem lida com sucesso e convertida para matriz RGB");}
	    			break;
                case 12:{ // rota��o direta
	    			System.out.println("> Defina o angulo de rota��o: ");
	    			sc1 = new Scanner(System.in);
	    			float angulo = sc1.nextFloat();

	    			int[][][] foto = Rotacionar(originalRgb,angulo);
	    			criarImagemRGB(foto,"fotoRotacionada");
	    			System.out.println("> Imagem Rotacionada com sucesso! \n");
	    			BufferedImage imagem1 = ImageIO.read(new File("C:\\Users\\Wellyngton\\Desktop\\Wellyngton\\19.2\\Int. ao Processamento Digital de Imagens\\Projeto 01\\Imagens Teste Projeto\\fotoRotacionada" + ".jpg"));
	    			exibirImagem(picture, imagem1, null, null);}
	    			break;

	    		case 13:{ //rota��o inversa
	    			System.out.println("> Defina o angulo de rota��o: ");
	    			sc1 = new Scanner(System.in);
	    			float angulo = sc1.nextFloat();

	    			int[][][] foto = RotacionarReverso(originalRgb,angulo);
	    			criarImagemRGB(foto,"fotoRotacionadaInversa");
	    			System.out.println("> Imagem Rotacionada com sucesso! \n");
	    			BufferedImage imagem1 = ImageIO.read(new File("C:\\Users\\Wellyngton\\Desktop\\Wellyngton\\19.2\\Int. ao Processamento Digital de Imagens\\Projeto 01\\Imagens Teste Projeto\\fotoRotacionadaInversa" + ".jpg"));
	    			exibirImagem(picture, imagem1, null, null);}
	    			break;

	    		case 14:{ // Imagem DCT
	    			double[][] doubleImage = intToDouble(originalRgb);
	    			double[][] dctAplicada = forwardDCT(doubleImage,doubleImage.length,doubleImage[0].length);
	    			System.out.println("Valor do n�vel DC:" + dctAplicada[0][0]);
	    			int [][][]fotoAjustada = normalizar(dctAplicada);
	    			criarImagemRGB(fotoAjustada,"fotoDCT");
	    			BufferedImage imagem1 = ImageIO.read(new File("C:\\Users\\Wellyngton\\Desktop\\Wellyngton\\19.2\\Int. ao Processamento Digital de Imagens\\Projeto 01\\Imagens Teste Projeto\\fotoDCT" + ".jpg"));
	    			exibirImagem(picture, imagem1, null, null);}
	    			break;
	    		case 15:{ // Imagem IDCT
	    			double[][] doubleImage = intToDouble(originalRgb);
	    			double[][] dctAplicada = forwardDCT(doubleImage,doubleImage.length,doubleImage[0].length);
	    			double[][] idctAplicada = forwardIDCT(dctAplicada,dctAplicada.length, dctAplicada[0].length);
	    			int[][][] fotoAjustada = doubleToInt(idctAplicada);
	    			criarImagemRGB(fotoAjustada,"fotoIDCT");
	    			BufferedImage imagem1 = ImageIO.read(new File("C:\\Users\\Wellyngton\\Desktop\\Wellyngton\\19.2\\Int. ao Processamento Digital de Imagens\\Projeto 01\\Imagens Teste Projeto\\fotoIDCT" + ".jpg"));
	    			exibirImagem(picture, imagem1, null, null);}
	    			break;
	    		case 16:{  // Frequencia de corte Fc
	    			System.out.println("Valor de Fc:");
	    			sc1 = new Scanner(System.in);
	    			int Fc = sc1.nextInt();

	    			double[][] doubleImage = intToDouble(originalRgb);
	    			double[][] dctAplicada = forwardDCT(doubleImage,doubleImage.length,doubleImage[0].length);
	    			double[][] filtro = filtrar(dctAplicada,Fc);
	    			double[][] idctAplicada = forwardIDCT(filtro,filtro.length, filtro[0].length);

	    			int[][][] fotoAjustada = doubleToInt(idctAplicada);
	    			criarImagemRGB(fotoAjustada,"fotoFiltroFC");
	    			BufferedImage imagem1 = ImageIO.read(new File("C:\\Users\\Wellyngton\\Desktop\\Wellyngton\\19.2\\Int. ao Processamento Digital de Imagens\\Projeto 01\\Imagens Teste Projeto\\fotoFiltroFC" + ".jpg"));
	    			exibirImagem(picture, imagem1, null, null);}
	    			break;
	    		case 17:{  // N maiores
	    			System.out.println("Valor de N:");
	    			sc1 = new Scanner(System.in);
	    			int N = sc1.nextInt();

	    			double[][] doubleImage = intToDouble(originalRgb);
	    			double[][] dctAplicada = forwardDCT(doubleImage,doubleImage.length,doubleImage[0].length);
	    			double[][] filtro = maioresCoeficientes(dctAplicada,N);
	    			double[][] idctAplicada = forwardIDCT(filtro,filtro.length, filtro[0].length);

	    			int[][][] fotoAjustada = doubleToInt(idctAplicada);
	    			criarImagemRGB(fotoAjustada,"NmaioresCoef");
	    			BufferedImage imagem1 = ImageIO.read(new File("C:\\Users\\Wellyngton\\Desktop\\Wellyngton\\19.2\\Int. ao Processamento Digital de Imagens\\Projeto 01\\Imagens Teste Projeto\\NmaioresCoef" + ".jpg"));
	    			exibirImagem(picture, imagem1, null, null);}
	    			break;
                default:
                    System.out.println("> Opcao inv�lida!\n");
			}
			System.out.println("Pressione Enter para continuar!");
            System.in.read();
			try{
                clearConsole();
            } catch (InterruptedException e){
                e.printStackTrace();
            }
			sc1.reset();
    	}while(true);
    }

    public static String iniciarSistema() throws IOException{
        System.out.println("> Insira o nome da imagem seguida da extens�o (Ex.: imagem.jpg)");
        sc1 = new Scanner(System.in);
        String s = sc1.nextLine();

        File file = new File("Insira seu diretório de imagens aqui" + s);
        while(!file.exists()){
            try{
                clearConsole();
            } catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println("> Arquivo inexistente!\n");
            System.out.println("> Insira o nome da imagem seguida da extens�o (Ex.: imagem.jpg)");
            sc1 = new Scanner(System.in);
            s = sc1.nextLine();
            file = new File("Insira seu diretório de imagens aqui" + s);
        }
        return s;
    }

    private static double[][] filtrar(double[][] dctAplicada, int fc) {
		double[][] output = new double [dctAplicada.length][dctAplicada[0].length];
		for(int j = 0; j < fc; j++) {
			for(int i = 0; i < fc; i++) {
				output[j][i] = dctAplicada[j][i];
			}
		}
		return output;
	}

	private static double[][] maioresCoeficientes(double[][] dctAplicada, int N) {
		int[] indiceMax = {-1,-1};
		double Max = 0;
		double MaxUltimo = 999999999;
		double aux=0;
		double[][] output = new double [dctAplicada.length][dctAplicada[0].length];

		for(int k = 0; k < N; k++) {
			Max=0;
			for(int j = 0; j < dctAplicada.length;j++) {
				for(int i = 0; i < dctAplicada[0].length; i++) {
					if(Math.abs(dctAplicada[j][i]) >= Max && Math.abs(dctAplicada[j][i]) < MaxUltimo) {
						indiceMax[0]=j;
						indiceMax[1]=i;
						aux = dctAplicada[j][i];
						Max = Math.abs(dctAplicada[j][i]);
					}
				}
			}
			if(indiceMax[0] != -1) {
				output[indiceMax[0]][indiceMax[1]] = aux;
			}
			MaxUltimo = Max;
			indiceMax[0]=-1;
			indiceMax[1]=-1;
		}
		return output;
	}

	private static int[][][] normalizar(double[][] dctAplicada) {
		int[][][] corrigido = new int[dctAplicada.length][dctAplicada[0].length][3];
		double max = 0;
		for(int j = 0; j < dctAplicada.length; j++) {
			for(int i = 0; i < dctAplicada[0].length; i++) {
				if(Math.log(Math.abs(dctAplicada[j][i]+1)) > max) {
					max = Math.log(Math.abs(dctAplicada[j][i]+1));
				}
			}
		}


		for(int j = 0; j < dctAplicada.length; j++) {
			for(int i = 0; i < dctAplicada[0].length; i++) {
				corrigido[j][i][0] = (int) (Math.log(Math.abs(dctAplicada[j][i]+1))*255/ max);
				corrigido[j][i][1] = (int) (Math.log(Math.abs(dctAplicada[j][i]+1))*255 / max);
				corrigido[j][i][2] = (int) (Math.log(Math.abs(dctAplicada[j][i]+1))*255 / max);
			}
		}

		return corrigido;
	}

	private static int[][][] doubleToInt(double[][] dctAplicada) {
		int [][][] imagem = new int[dctAplicada.length][dctAplicada[0].length][3];
		for(int j = 0; j < dctAplicada.length; j++) {
			for(int i = 0; i < dctAplicada[0].length; i++) {
				imagem[j][i][0] = (int) dctAplicada[j][i];
				imagem[j][i][1] = (int) dctAplicada[j][i];
				imagem[j][i][2] = (int) dctAplicada[j][i];
			}
		}
		return imagem;
	}

	private static int[][][] imageToRgbMatrix(BufferedImage image) { // converte Imagem para matriz RGB
        int w = image.getWidth();
        int h = image.getHeight();
        int[][][] matriz = new int[h][w][3];

        // Percorre imagem pixel por pixel e transforma em RGB int[3]
        for (int j = 0; j < h; j++) {
          for (int i = 0; i < w; i++) {
            int pixel = image.getRGB(i, j);
            matriz[j][i] = pixelToRGB(pixel);
          }
        }
        return matriz;
      }

    public static int[] pixelToRGB(int pixel) { // separa os bytes do int (cada cor possui 1 byte dos 4 no inteiro)
      //  int alpha = (pixel >> 24) & 0xff;
        int red = (pixel >> 16) & 0xff;
        int green = (pixel >> 8) & 0xff;
        int blue = (pixel) & 0xff;
        int[] rgb = new int[3];
        rgb[0] = red;
        rgb[1] = green;
        rgb[2] = blue;
        return rgb;
    }

    public static double[][][] rgbToYiq(int[][][] rgb) { //RGB-> YIQ
    	double [][][] Yiq = new double[rgb.length][rgb[0].length][3];
    	for (int j = 0; j < rgb.length; j++) {
            for (int i = 0; i < rgb[0].length; i++) {
              Yiq[j][i][0] = (0.299 * rgb[j][i][0]) + (0.587 * rgb[j][i][1]) + (0.114 * rgb[j][i][2]);
              Yiq[j][i][1] = (0.596 * rgb[j][i][0]) - (0.274 * rgb[j][i][1]) - (0.322 * rgb[j][i][2]);
              Yiq[j][i][2] = (0.211 * rgb[j][i][0]) - (0.523 * rgb[j][i][1]) + (0.312 * rgb[j][i][2]);
            }
          }

    	return Yiq;
      }

    public static int[][][] yiqToRgb(double[][][] yiq) { // YIQ-> RGB
    	int [][][] rgb = new int[yiq.length][yiq[0].length][3];
    	for (int j = 0; j < yiq.length; j++) {
            for (int i = 0;i < yiq[0].length; i++) {
              rgb[j][i][0] = (int) (1 * yiq[j][i][0] + 0.956 * yiq[j][i][1] + 0.621 * yiq[j][i][2]);
              rgb[j][i][1] = (int) (1 * yiq[j][i][0] - 0.272 * yiq[j][i][1] - 0.647 * yiq[j][i][2]);
              rgb[j][i][2] = (int) (1 * yiq[j][i][0] - 1.106 * yiq[j][i][1] + 1.703 * yiq[j][i][2]);
            }
          }
    	return rgb;
      }

    public static void criarImagemRGB(int[][][] ImagemRGB, String name) throws IOException { // cria imagem (internet)
        BufferedImage imagemRGB = new BufferedImage(ImagemRGB[0].length, ImagemRGB.length, BufferedImage.TYPE_INT_RGB);
        for (int j = 0; j < ImagemRGB.length; j++) {
            for (int i = 0; i < ImagemRGB[0].length; i++) {

            	if(ImagemRGB[j][i][0] > 255) { // garante os limites de RGB
					ImagemRGB[j][i][0] = 255;
				}
			   else if(ImagemRGB[j][i][0] < 0) {
				   ImagemRGB[j][i][0] = 0;
			   }
			   if(ImagemRGB[j][i][1] > 255) {
				   ImagemRGB[j][i][1] = 255;
			   }
			   else if(ImagemRGB[j][i][1] < 0) {
				   ImagemRGB[j][i][1] = 0;
			   }

			   if(ImagemRGB[j][i][2] > 255) {
				   ImagemRGB[j][i][2] = 255;
			   }
			   else if(ImagemRGB[j][i][2] < 0) {
				   ImagemRGB[j][i][2] = 0;
			   }

               int rgb = ImagemRGB[j][i][0]; // vermelho
               rgb = (rgb << 8) + ImagemRGB[j][i][1]; // + green
               rgb = (rgb << 8) + ImagemRGB[j][i][2]; // + azul
               imagemRGB.setRGB(i, j, rgb);
            }
         }
        File outputFile = new File("Insira o diretório de saída aqui"+ name + ".jpg");
        ImageIO.write(imagemRGB, "jpg", outputFile);
    }


    private static int[][][] filtrarCorMono(int[][][] matriz,int cor) { // separar bandas monocrom�ticas
    	int[][][] matrizRgb = new int[matriz.length][matriz[0].length][3]; // imagem de tamanho igual
    	for (int j = 0; j < matriz.length; j++) {
            for (int i = 0; i < matriz[0].length; i++) {
            	 matrizRgb[j][i][0] = matriz[j][i][cor];
                 matrizRgb[j][i][1] = matriz[j][i][cor];
                 matrizRgb[j][i][2] = matriz[j][i][cor];
            }
          }
		return matrizRgb;
	}

    private static int[][][] filtrarCor(int[][][] matriz,int cor) { // separar bandas coloridas
    	int[][][] matrizRgb = new int[matriz.length][matriz[0].length][3]; // imagem de tamanho igual
    	for (int j = 0; j < matriz.length; j++) {
            for (int i = 0; i < matriz[0].length; i++) {
            	 matrizRgb[j][i][0] = 0;
                 matrizRgb[j][i][1] = 0;
                 matrizRgb[j][i][2] = 0;
                 matrizRgb[j][i][cor] = matriz[j][i][cor];
            }
          }

		return matrizRgb;
	}
    public static int[][][] alterarBrilho(int[][][] rgb, double brilho, int banda) { // brilho para RGB
    	for (int j = 0; j < rgb.length; j++) {
            for (int i = 0; i < rgb[0].length; i++) {
            	if(banda ==3) {
            	 rgb[j][i][0] = (int) ((int) rgb[j][i][0] * (brilho/100));
                 rgb[j][i][1] = (int) ((int) rgb[j][i][1] * (brilho/100));
                 rgb[j][i][2] = (int) ((int) rgb[j][i][2] * (brilho/100));
            	} else
               	 rgb[j][i][banda] = (int) ((int) rgb[j][i][banda] * (brilho/100));
            }
          }
    	return rgb;
    }
    public static int[][][] negativo(int[][][] rgb, int banda) { // negativo pra RGB
    	for (int j = 0; j < rgb.length; j++) {
    		for (int i = 0; i < rgb[0].length; i++) {
    			if(banda == 3) { // rgb todo
           	 		rgb[j][i][0] = 255-rgb[j][i][0];
           	 		rgb[j][i][1] = 255-rgb[j][i][1];
           	 		rgb[j][i][2] = 255-rgb[j][i][2];

    			}else  				// s� na banda
       	 		rgb[j][i][banda] = 255-rgb[j][i][banda];
    		}
    	}
    	return rgb;
    }

    public static double[][][] negativoYiq(double[][][] yiq) { // negativo pra yiq
    	for (int j = 0; j < yiq.length; j++) {
    		for (int i = 0; i < yiq[0].length; i++) {
    			yiq[j][i][0] = 255-yiq[j][i][0];
    		}
    	}
    	return yiq;
    }

    public static double[][][] brilhoYiq(double[][][] yiq,double brilho) { // multiplicador de brilho pra yiq
    	for (int j = 0; j < yiq.length; j++) {
    		for (int i = 0; i < yiq[0].length; i++) {
    			yiq[j][i][0] = (brilho/100)*yiq[j][i][0];
    		}
    	}
    	return yiq;
    }

    //Cria uma borda preta ao redor da foto
    public static int[][][] ajustarFoto(int[][][] rgb, int iSize, int jSize){ // colocar bordas ao redor da imagem

    	int [][][] fotoAjustada = new int[rgb.length+jSize-1][rgb[0].length+iSize-1][3];  // declaro uma imagem com bordas

    	for (int j = 0; j < rgb.length+jSize-1; j++) {
    		for (int i =  0; i < rgb[0].length+iSize-1; i++) {
    			if(i >= iSize/2  && j >= jSize/2  && j < rgb.length +jSize/2 && i < rgb[0].length + iSize/2) { // atribuindo imagem original ao centro
					fotoAjustada[j][i][0] = rgb[j-jSize/2][i-iSize/2][0];
					fotoAjustada[j][i][1] = rgb[j-jSize/2][i-iSize/2][1];
					fotoAjustada[j][i][2] = rgb[j-jSize/2][i-iSize/2][2];
    			} else { // colocando bordas
    				fotoAjustada[j][i][0] = 0;
					fotoAjustada[j][i][1] = 0;
					fotoAjustada[j][i][2] = 0;
    			}
    		}
    	}
    	return fotoAjustada;
    }

    public static int[][][] media(int[][][] rgb, int iSize, int jSize){	 // janela deslizante + m�dia
    	int[][][] saida = new int[rgb.length-jSize+1][rgb[0].length-iSize+1][3];
    	double[][] filtro = new double[jSize][iSize];
    	double somaR=0, somaG=0,somaB=0;
    	double porcentagem = 0;
        double resultado = 0;
    	for(int j=0;j < jSize; j++){
    		for(int i=0; i < iSize; i++) {
    			filtro[j][i] = (1.0/(iSize*jSize));
    		}
    	}

    	for (int j = jSize/2; j < rgb.length - jSize/2 ; j++) {
    		if(porcentagem%40 == 0){
                resultado = porcentagem/rgb.length*100;
    			System.out.printf("%.2f", resultado);
    			System.out.println("%");
    		}
    		for (int i = iSize/2; i < rgb[0].length - iSize/2; i++) {
    			somaR=0;somaG=0;somaB=0;
				for(int y=0; y < jSize; y++) {
					for(int x=0; x < iSize; x++) {
						somaR +=  (rgb[j+y-jSize/2][i+x-iSize/2][0] * filtro[y][x]);
	    				somaG +=  (rgb[j+y-jSize/2][i+x-iSize/2][1] * filtro[y][x]);
	    				somaB +=  (rgb[j+y-jSize/2][i+x-iSize/2][2] * filtro[y][x]);
					}
				}
    			saida[j-jSize/2][i-iSize/2][0] = (int) somaR;
    			saida[j-jSize/2][i-iSize/2][1] = (int) somaG;
    			saida[j-jSize/2][i-iSize/2][2] = (int) somaB;
    		}
    		porcentagem++;
    	}
    	return saida;
    }

    public static float[][] lerFiltro(String arquivo) throws FileNotFoundException{ // parsing do txt
    	sc = new Scanner(new BufferedReader(new FileReader("Insira o diretório de filtros aqui"+ arquivo + ".txt")));

        int rows = sc.nextInt(); //j
        int columns = sc.nextInt(); // i
        float [][] myArray = new float[rows][columns];
        sc.nextLine();
        while(sc.hasNextLine()) {
           for (int i=0; i<myArray.length; i++) {
              String[] line = sc.nextLine().trim().split(" ");
              for (int j=0; j<line.length; j++) {
                 myArray[i][j] = Float.parseFloat(line[j]);
              }
           }
        }
        System.out.println(Arrays.deepToString(myArray));

		return myArray;
    }

    public static int[][][] aplicarFiltro(int[][][]rgb,float[][] filtro) { // janela deslizante + filtro TXT
    	int jSize = filtro.length;
    	int iSize = filtro[0].length;
    	int[][][] saida = new int[rgb.length-jSize+1][rgb[0].length-iSize+1][3]; // reduz ao tamanho original


    	double somaR=0, somaG=0,somaB=0;
    	for (int j = jSize/2; j < rgb.length - jSize/2 ; j++) {
    		for (int i = iSize/2; i < rgb[0].length - iSize/2; i++) {

    			somaR=0;somaG=0;somaB=0;
				for(int y=0; y < jSize; y++) {
					for(int x=0; x < iSize; x++) {
						somaR +=  (rgb[j+y-jSize/2][i+x-iSize/2][0] * filtro[y][x]);
	    				somaG +=  (rgb[j+y-jSize/2][i+x-iSize/2][1] * filtro[y][x]);
	    				somaB +=  (rgb[j+y-jSize/2][i+x-iSize/2][2] * filtro[y][x]);
					}
				}
    			saida[j-jSize/2][i-iSize/2][0] = (int) somaR;
    			saida[j-jSize/2][i-iSize/2][1] = (int) somaG;
    			saida[j-jSize/2][i-iSize/2][2] = (int) somaB;
    		}
    	}
		return saida;
    }

    public static int[][][] moda(int[][][] rgb, int iSize, int jSize){ // janela deslizante + moda
    	int[][][] saida = new int[rgb.length-jSize+1][rgb[0].length-iSize+1][3];
    	int[][] contadores = new int[256][3];
    	double porcentagem = 0;
        double resultado = 0;

    	for (int j = jSize/2; j < rgb.length - jSize/2  ; j++) {
    		if(porcentagem%40 == 0){
                resultado = porcentagem/rgb.length*100;
    			System.out.printf("%.2f", resultado);
    			System.out.println("%");
    		}
    		for (int i = iSize/2; i < rgb[0].length - iSize/2 ; i++) {
    			contadores = new int[256][3];
    			for(int y=0; y < jSize; y++) {
					for(int x=0; x < iSize; x++) {
						contadores[  rgb[j+y-jSize/2][i+x-iSize/2][0]  ][0]++;
						contadores[  rgb[j+y-jSize/2][i+x-iSize/2][1]   ][1]++;
						contadores[  rgb[j+y-jSize/2][i+x-iSize/2][2]   ][2]++;
						//percorreu o filtro todo, agora precisa achar o maior de cada banda
					}
				}
    				saida[j-jSize/2][i-iSize/2][0]= max(contadores,0); //r
    				saida[j-jSize/2][i-iSize/2][1]= max(contadores,1); //g
    				saida[j-jSize/2][i-iSize/2][2]= max(contadores,2); //b
    		}
    		porcentagem++;
    	}
    	return saida;
    }

    public static int max(int[][]contadores, int tipo) {
    	int max=0, ind=-1;
    	for(int i=0;i<255;i++) {
    		if(max < contadores[i][tipo]) {
    			max = contadores[i][tipo];
    			ind = i;
    		}
    	}
    	return ind;
    }

    public static int[][][] mediana(int[][][] rgb, int iSize, int jSize){ // janela deslizante + mediana
    	int[][][] saida = new int[rgb.length-jSize+1][rgb[0].length-iSize+1][3]; // remover borda
    	int tamanho = (jSize)*(iSize);
    	double porcentagem = 0;
    	double resultado = 0;
    	int[] valoresR = new int[tamanho];
    	int[] valoresG = new int[tamanho];
    	int[] valoresB = new int[tamanho];

    	for (int j = jSize/2; j < rgb.length - jSize/2 ; j++) {
    		if(porcentagem%40 == 0) {
                resultado = porcentagem/j*100;
    			System.out.printf("%.2f", resultado);
    			System.out.println("%");
    		}
    		for (int i = iSize/2; i < rgb[0].length - iSize/2; i++) {
    			int k=0;
    			for(int y=0; y < jSize; y++) {
					for(int x=0; x < iSize; x++) {
						valoresR[k] = rgb[j+y-jSize/2][i+x-iSize/2][0];
						valoresG[k] = rgb[j+y-jSize/2][i+x-iSize/2][1];
						valoresB[k] = rgb[j+y-jSize/2][i+x-iSize/2][2];
						k++;
					}
				}
    				saida[j-jSize/2][i-iSize/2][0]= valorMediana(valoresR);
    				saida[j-jSize/2][i-iSize/2][1]= valorMediana(valoresG);
    				saida[j-jSize/2][i-iSize/2][2]= valorMediana(valoresB);
    		}
    		porcentagem++;
    	}
    	return saida;
    }

    public static int valorMediana(int[]valores) {
    	int mediana;
    	Arrays.sort(valores);
    	if(valores.length % 2 == 1) { //impar
    		mediana = valores[valores.length/2];
    	}
    	else
    		mediana = (valores[valores.length/2] + valores[valores.length/2+1]) / 2;
    	return mediana;
    }

    private static int[][][] Rotacionar(int[][][] matriz,double angulo) { // separar bandas mono
    	int ic = Math.round(matriz[0].length/2), jc = Math.round(matriz.length/2);
    	//C�lculo de quanto o H e W s�o deslocados
    	int deslocaX=0, deslocaY=0;
    	if(angulo%360 >= 0 && angulo%360 <= 90 ) { // quem mexe no X � (0,i) usando a formula para i'; para Y � (j,i)
    		deslocaY = (int) (Math.round((matriz[0].length-ic)*Math.sin(Math.toRadians(angulo)) + (matriz.length - jc)* Math.cos(Math.toRadians(angulo)) + jc ) - matriz.length)*2;
    		deslocaX = (int) (Math.round((matriz[0].length-ic)*Math.cos(Math.toRadians(angulo)) - (0 - jc)* Math.sin(Math.toRadians(angulo)) + ic) - matriz[0].length)*2;
    	}
    	if(angulo%360 > 90 && angulo%360 <= 180 ) { // quem mexe no X � (0,0) usando a formula para i'; para Y � (0,i)
    		deslocaY = (int) (Math.round((matriz[0].length-ic)*Math.sin(Math.toRadians(angulo)) + (0-jc)*Math.cos(Math.toRadians(angulo)) + jc) - matriz.length) *2;
    		deslocaX = (int) (Math.round((0-ic)*Math.cos(Math.toRadians(angulo)) - (0-jc)*Math.sin(Math.toRadians(angulo)) + ic) - matriz[0].length)*2;
    	}
    	if(angulo%360 > 180 && angulo%360 <= 270 ) { // quem mexe no X � (0,j) usando a formula para i'; para Y � (0,0)
    		deslocaY = (int) (Math.round((0-ic)*Math.sin(Math.toRadians(angulo)) + (0 - jc)* Math.cos(Math.toRadians(angulo)) + jc ) - matriz.length)*2;
    		deslocaX = (int) (Math.round((0-ic)*Math.cos(Math.toRadians(angulo)) - (matriz.length - jc)* Math.sin(Math.toRadians(angulo)) + ic) - matriz[0].length)*2;
    	}
    	if(angulo%360 > 270 && angulo%360 <= 360 ) { // quem mexe no X � (j,i) usando a formula par i'; para Y � (j,0)
    		deslocaY = (int) (Math.round((0-ic)*Math.sin(Math.toRadians(angulo)) + (matriz.length - jc)* Math.cos(Math.toRadians(angulo)) + jc ) - matriz.length)*2;
    		deslocaX = (int) (Math.round((matriz[0].length-ic)*Math.cos(Math.toRadians(angulo)) - (matriz.length - jc)* Math.sin(Math.toRadians(angulo)) + ic) - matriz[0].length)*2;
    	}

    	int[][][] matrizRgb = new int[matriz.length+deslocaY+1][matriz[0].length+deslocaX+1][3]; // imagem de tamanho igual
    	int i1,j1;
    	for (int j = 0; j < matriz.length; j++) {
            for (int i = 0; i < matriz[0].length; i++) {
            	i1 = (int) Math.round((i-ic)*Math.cos(Math.toRadians(angulo)) - (j-jc)*Math.sin(Math.toRadians(angulo)) + ic);
            	j1 = (int) Math.round((i-ic)*Math.sin(Math.toRadians(angulo)) + (j-jc)*Math.cos(Math.toRadians(angulo)) + jc);
            	matrizRgb[j1+deslocaY/2][i1+deslocaX/2] =  matriz[j][i];
            }
          }
		return matrizRgb;
	}

    private static int[][][] RotacionarReverso(int[][][] matriz,double angulo) { // separar bandas mono
    	int ic = Math.round(matriz[0].length/2), jc = Math.round(matriz.length/2);
    	//C�lculo de quanto o H e W s�o deslocados
    	int deslocaX=0, deslocaY=0;
    	if(angulo%360 >= 0 && angulo%360 <= 90 ) { // quem mexe no X � (0,i) usando a formula para i'; para Y � (j,i)
    		deslocaY = (int) (Math.round((matriz[0].length-ic)*Math.sin(Math.toRadians(angulo)) + (matriz.length - jc)* Math.cos(Math.toRadians(angulo)) + jc ) - matriz.length)*2;
    		deslocaX = (int) (Math.round((matriz[0].length-ic)*Math.cos(Math.toRadians(angulo)) - (0 - jc)* Math.sin(Math.toRadians(angulo)) + ic) - matriz[0].length)*2;
    	}
    	if(angulo%360 > 90 && angulo%360 <= 180 ) { // quem mexe no X � (0,0) usando a formula para i'; para Y � (0,i)
    		deslocaY = (int) (Math.round((matriz[0].length-ic)*Math.sin(Math.toRadians(angulo)) + (0-jc)*Math.cos(Math.toRadians(angulo)) + jc) - matriz.length) *2;
    		deslocaX = (int) (Math.round((0-ic)*Math.cos(Math.toRadians(angulo)) - (0-jc)*Math.sin(Math.toRadians(angulo)) + ic) - matriz[0].length)*2;
    	}
    	if(angulo%360 > 180 && angulo%360 <= 270 ) { // quem mexe no X � (0,j) usando a formula para i'; para Y � (0,0)
    		deslocaY = (int) (Math.round((0-ic)*Math.sin(Math.toRadians(angulo)) + (0 - jc)* Math.cos(Math.toRadians(angulo)) + jc ) - matriz.length)*2;
    		deslocaX = (int) (Math.round((0-ic)*Math.cos(Math.toRadians(angulo)) - (matriz.length - jc)* Math.sin(Math.toRadians(angulo)) + ic) - matriz[0].length)*2;
    	}
    	if(angulo%360 > 270 && angulo%360 < 360 ) { // quem mexe no X � (j,i) usando a formula par i'; para Y � (j,0)
    		deslocaY = (int) (Math.round((0-ic)*Math.sin(Math.toRadians(angulo)) + (matriz.length - jc)* Math.cos(Math.toRadians(angulo)) + jc ) - matriz.length)*2;
    		deslocaX = (int) (Math.round((matriz[0].length-ic)*Math.cos(Math.toRadians(angulo)) - (matriz.length - jc)* Math.sin(Math.toRadians(angulo)) + ic) - matriz[0].length)*2;
    	}

    	int[][][] matrizRgb = new int[matriz.length+deslocaY+1][matriz[0].length+deslocaX+1][3]; // imagem de tamanho igual
    	int i,j;
    	for (int j1 = -deslocaY/2; j1 < matrizRgb.length-deslocaY/2; j1++) {
            for (int i1 = -deslocaX/2; i1 < matrizRgb[0].length-deslocaX/2; i1++) {
            	i = (int) ((i1-ic)*Math.cos(Math.toRadians(angulo)) + (j1-jc)*Math.sin(Math.toRadians(angulo)) + ic);
            	j = (int) (-(i1-ic)*Math.sin(Math.toRadians(angulo)) + (j1-jc)*Math.cos(Math.toRadians(angulo)) + jc);

            	if(i <matriz[0].length && j < matriz.length && i >=0 && j >=0) { // alguns valores estavam dando negativos
            		matrizRgb[j1+deslocaY/2][i1+deslocaX/2] = matriz[j][i];
            	}
           }
    	}
		return matrizRgb;
	}

    public static double[][] forwardDCT(double[][] input, int R,int C) {
        double[][] output = new double[R][C];
        double[] aux = new double[input[0].length];
        double[] aux2 = new double[input.length];


        // Aplicar nas linhas
        for(int i = 0; i < R; i++) {
        	output[i] = DCT(input[i], input[0].length);

        }

        //Separar Colunas e aplicar nelas
        for(int j = 0; j < C; j++) {
        	aux2 = getColumn(output,j);
        	aux = DCT(aux2, aux2.length);

        	for(int i = 0 ; i < aux.length; i++) {
        		output[i][j] = aux[i];
        	}

        }
        return output;
    }

    public static double[][] forwardIDCT(double[][] input, int R,int C) {
        double[][] output = new double[R][C];
        double[] aux = new double[input[0].length];
        double[] aux2 = new double[input.length];


        // Aplicar nas linhas
        for(int i = 0; i < R; i++) {
        	output[i] = IDCT(input[i], input[0].length);

        }

        //Separar Colunas e aplicar nelas
        for(int j = 0; j < C; j++) {
        	aux2 = getColumn(output,j);
        	aux = IDCT(aux2, aux2.length);

        	for(int i = 0 ; i < aux.length; i++) {
        		output[i][j] = aux[i];
        	}

        }
        return output;
    }

    public static double[][] intToDouble(int[][][] matrizRgb){
    	double[][] output = new double [matrizRgb.length][matrizRgb[0].length];
    	for(int j = 0; j < matrizRgb.length; j++) {
    		for(int i = 0; i < matrizRgb[0].length; i++) {
    			output[j][i] = matrizRgb[j][i][0];
    		}
    	}
    	return output;
    }

    public static double [] DCT(double [] x, int tamanho){
       double [][] xk = new double[tamanho][tamanho];

       double [] X = new double[tamanho];

       for( int n = 0; n < tamanho; n++ )
          xk[0][n] = 1/Math.sqrt(tamanho) * x[n];

       for( int k = 1; k < tamanho; k ++ )
          for( int n = 0; n < tamanho; n ++ )
             xk[k][n] = Math.sqrt(2.0/tamanho) * Math.cos(k * Math.PI/(2.0 * tamanho)*(2.0 * n+1)) * x[n];

       for( int k = 0; k < tamanho; k++ )
          X[k] = 0;

       for( int k = 0; k < tamanho; k++ )
          for( int n = 0; n < tamanho; n++)
             X[k]+=xk[k][n];

       return X;
    }

    public static double [] IDCT( double [] X, int tamanho )
    {
       double [][] xk = new double [tamanho][tamanho];
       double [] x = new double[tamanho];
       for(int n=0;n<tamanho;n++)
          xk[0][n]= Math.sqrt(1.0/tamanho) * X[0] * Math.cos(0);
       for(int k=1;k<tamanho;k++)
          for(int n=0;n<tamanho;n++)
             xk[k][n] = Math.sqrt(2.0/tamanho) * X[k] * Math.cos(k*(2*n+1)*Math.PI/(2.0*tamanho));
       for(int k=0;k<tamanho;k++)
          X[k]=0;
       for(int k=0;k<tamanho;k++)
          for(int n=0;n<tamanho;n++)
             x[k]+=xk[n][k];
       return x;
    }

    public static double[] getColumn(double[][] array, int index){
        double[] column = new double[array.length]; // Here I assume a rectangular 2D array!
        for(int i=0; i<column.length; i++){
           column[i] = array[i][index];
        }
        return column;
    }

    public static void exibirImagem(BufferedImage image, BufferedImage image1, BufferedImage image2, BufferedImage image3){
        if(image == null){

        }else{
            ImageIcon icon = new ImageIcon(image);
            JLabel imageLabel = new JLabel(icon);
            JFrame frame = new JFrame();
            Container contentPane = frame.getContentPane();
            contentPane.setLayout(new GridLayout());
            contentPane.add(new JScrollPane(imageLabel));
            frame.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
            frame.setSize(800, 400);
            frame.setVisible(true);
        }
        if(image1 == null){

        }else{
            ImageIcon icon1 = new ImageIcon(image1);
            JLabel imageLabel1 = new JLabel(icon1);
            JFrame frame1 = new JFrame();
            Container contentPane1 = frame1.getContentPane();
            contentPane1.setLayout(new GridLayout());
            contentPane1.add(new JScrollPane(imageLabel1));
            frame1.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
            frame1.setSize(800, 400);
            frame1.setVisible(true);
        }
        if(image2 == null){

        }else{
            ImageIcon icon2 = new ImageIcon(image2);
            JLabel imageLabel2 = new JLabel(icon2);
            JFrame frame2 = new JFrame();
            Container contentPane2 = frame2.getContentPane();
            contentPane2.setLayout(new GridLayout());
            contentPane2.add(new JScrollPane(imageLabel2));
            frame2.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
            frame2.setSize(800, 400);
            frame2.setVisible(true);
        }
        if(image3 == null){

        }else{
            ImageIcon icon3 = new ImageIcon(image3);
            JLabel imageLabel3 = new JLabel(icon3);
            JFrame frame3 = new JFrame();
            Container contentPane3 = frame3.getContentPane();
            contentPane3.setLayout(new GridLayout());
            contentPane3.add(new JScrollPane(imageLabel3));
            frame3.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
            frame3.setSize(800, 400);
            frame3.setVisible(true);
        }
    }

    public final static void clearConsole() throws InterruptedException, IOException{
        if (System.getProperty("os.name").contains("Windows"))
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        else
            Runtime.getRuntime().exec("clear");
    }

}

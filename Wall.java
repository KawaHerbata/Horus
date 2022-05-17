
import java.util.List;
import java.util.Optional;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author julia
 */
interface Structure {
// zwraca dowolny element o podanym kolorze
    Optional findBlockByColor(String color);

// zwraca wszystkie elementy z danego materiału
    List findBlocksByMaterial(String material);

//zwraca liczbę wszystkich elementów tworzących strukturę
    int count();
}

public class Wall implements Structure {
    private List blocks;
    }

    interface Block {
    String getColor();
    String getMaterial();
    }

    interface CompositeBlock extends Block {
    List getBlocks();

    public Optional findBlockByColor(String color)
    {
        int block_id = -1;
        int comBlock_id = -1;
        Optional<Block> answer;
        if(blocks != null)
        {
            for (int i = 0 ; i < blocks.size(); i++)
            {
                if(blocks.get(i) instanceof CompositeBlock)
                {
                    List pom = blocks.get(i).getBlocks();
                    for (int j = 0 ; j < pom.size(); j++)
                    {
                        if(pom.get(j).getColour().equals(color))
                        {
                            block_id = i;
                            comBlock_id = j;
                        
                        }
                    }
                }
                else
                {
                    if(blocks.get(i).getColour().equals(color))
                    {
                        block_id = i;
                    }
                }
            }
        }
    
        if(block_id > -1)
        {
            if(comBlock_id < 0)
                answer = Optional.of(blocks.get(block_id));
            else
            {
                answer = Optional.of(blocks.get(block_id).getBlocks().get(comBlock_id));
            }
        }
        else
        {
            answer = Optional.of(null);
        }
 
        return answer;
    }

    public int count()
    {
        int number = 0;
        if(blocks != null)
        {
            for(int i = 0; i < blocks.size(); i++)
            {
                if(blocks.get(i) instanceof CompositeBlock)
                {
                    number = number + blocks.get(i).getBlocks().size();
                }
                else
                    number++;
            }
        }
        return number;
    }
    
    public List findBlocksByMaterial(String material)
    {
        List lista = new ArrayList<Block>();
        
        for (int i = 0; i < block.size(); i++)
        {
            if(blocks.get(i) instanceof CompositeBlock)
            {
                Lista pom = blocks.get(i).getBlocks();
                for(int j = 0; j < pom.size(); j++)
                {
                    if(pom.get(j).getMateiral().equals(material))
                        lista.add(pom.get(j));
                }
            }
            else
            {
                if(blocks.get(i).getMaterial().equals(material))
                    lista.add(blocks.get(i));
            }
        }
        
        return lista;
    }

}
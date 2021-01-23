package jpabook.jpashop.domain.item;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    public void save(Item item){
        if(item.getId() == null) {
            em.persist(item);
        } else {
            em.merge(item);
        }
    }

    /**
     * 아이템 조회(단건)
     * @param id
     * @return 아이템
     */
    public Item findOne(Long id){
        return em.find(Item.class, id);
    }

    /**
     * 아이템 조회(모두)
     * @return 아이템 리스트
     */
    public List<Item> findAll(){
        return em.createQuery("select i from Item i", Item.class).getResultList();
    }
}
